package com.blueocn.api.service;

import com.blueocn.api.response.ValidateResponse;
import com.blueocn.api.vo.UserVo;

/**
 * Title: RegisterService
 * Description: 验证码生成确认服务
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-01-09 15:09
 */
public interface ValidateService {

    /**
     * 生成验证码, 并将验证码存入Redis和数据库中
     *
     * @param userId 用户ID
     * @return 包含时间戳的验证码
     * @throws NullPointerException 用户 userId 为空
     */
    String generateCode(Long userId) throws NullPointerException;


    /**
     * 从Redis中查找验证码(Redis Key)对应的用户ID(Redis Value), 如果Redis中不存在.
     * 则判断验证码时间, 如果未超时, 则从数据库中读取
     * 能读取到用户ID的话, 则将数据库中对应用户的验证记录删除.
     *
     * @param code 验证码
     * @return 验证码对应的用户ID, 如果为空, 则表明验证失败
     */
    ValidateResponse isCodeValidate(String code);

    /**
     * 根据用户信息发送激活邮件
     *
     * @param userVo 用户对象
     */
    void sendValidateEmail(UserVo userVo, Long userId);
}
