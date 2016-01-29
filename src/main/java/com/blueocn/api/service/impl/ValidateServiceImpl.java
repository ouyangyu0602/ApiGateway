package com.blueocn.api.service.impl;

import com.blueocn.api.repository.domain.ValidateInfoEntity;
import com.blueocn.api.repository.mapper.ValidateInfoRepo;
import com.blueocn.api.service.MailService;
import com.blueocn.api.service.ValidateService;
import com.blueocn.api.response.ValidateResponse;
import com.blueocn.api.support.utils.EmailUtils;
import com.blueocn.api.support.utils.ValidateCodeUtils;
import com.blueocn.api.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import static com.blueocn.api.support.utils.RedisKeyUtils.validateKey;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Title: ValidateServiceImpl
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-01-11 22:17
 */
@Service
public class ValidateServiceImpl implements ValidateService {

    private static final Integer EXPIRED_TIME = 30; // Minutes

    @Autowired
    private RedisTemplate<String, Long> redisTemplate;

    @Autowired
    private ValidateInfoRepo validateInfoRepo;

    @Autowired
    private MailService mailService;

    @Value("${systemBaseUrl}")
    private String systemBaseUri;

    @Value("${mail.username}")
    private String mailAddress;

    @Override
    public String generateCode(Long userId) throws NullPointerException {
        if (userId != null) {
            String validateCode = ValidateCodeUtils.generateCode(EXPIRED_TIME);
            // Redis 存储
            redisTemplate.opsForValue()
                .set(validateKey(validateCode), userId, EXPIRED_TIME, TimeUnit.MINUTES);

            // 数据库存储
            ValidateInfoEntity info = new ValidateInfoEntity();
            info.setUserId(userId);
            info.setValidateCode(validateCode);
            validateInfoRepo.add(info);

            return validateCode;
        }
        throw new NullPointerException("用户ID不能为空");
    }

    @Override
    public ValidateResponse isCodeValidate(String code) {
        checkNotNull(code);
        return getValidateInfoFromRedisOrDatabase(code);
    }

    @Override
    public void sendValidateEmail(UserVo userVo, Long userId) {
        String validateCode = generateCode(userId);
        mailService.sendMail(EmailUtils.getRegisterEmail(userVo, validateCode, systemBaseUri, mailAddress));
    }

    /**
     * 从Redis中获取验证码对应的验证信息, 如果获取到, 则将Redis中对应验证码验证信息全部删除
     *
     * @param validateCode 验证码
     * @return 用户ID
     */
    private ValidateResponse getValidateInfoFromRedisOrDatabase(String validateCode) {
        Long userId = redisTemplate.opsForValue().get(validateKey(validateCode));
        if (userId != null) {
            redisTemplate.delete(validateKey(validateCode));
            validateInfoRepo.deleteByUserId(userId); // 会不会有问题啊?

            ValidateResponse response = new ValidateResponse();
            response.setUserId(userId);
            response.setSuccess(false);
            return response;
        }
        return getValidateInfoFromDateBase(validateCode);
    }

    /**
     * 从数据库中获取验证码对应的验证信息, 如果获取到, 则将用户对应的验证信息全部删除
     *
     * @param validateCode 验证码
     * @return 用户ID
     */
    private ValidateResponse getValidateInfoFromDateBase(String validateCode) {
        ValidateResponse response = new ValidateResponse();

        ValidateInfoEntity validate = validateInfoRepo.queryInfoByCode(validateCode);
        if (validate != null) {
            Long userId = validate.getUserId();
            validateInfoRepo.deleteByUserId(userId); // 会不会有问题啊?
            response.setUserId(userId);
        }
        if (ValidateCodeUtils.isCodeNotExpired(validateCode)) {
            response.setSuccess(true);
        }
        return response;
    }
}
