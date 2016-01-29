package com.blueocn.api.service.impl;

import com.blueocn.api.enums.UserStatusEnum;
import com.blueocn.api.enums.UserTypeEnum;
import com.blueocn.api.repository.domain.UserEntity;
import com.blueocn.api.repository.mapper.UserRepo;
import com.blueocn.api.response.UserResponse;
import com.blueocn.api.service.UserService;
import com.blueocn.api.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.blueocn.api.enums.UserStatusEnum.AWAIT;
import static com.blueocn.api.support.utils.password.PasswordUtils.*;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.collections.CollectionUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Title: UserServiceImpl
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-17 22:50
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserRepo userRepository;

    @Override
    public UserResponse login(String username, String password) {
        List<UserEntity> existUser = queryUserByIdentityOrEmail(username, username);
        if (isNotEmpty(existUser)) {
            for (UserEntity user : existUser) {
                checkUserStatus(user);
                if (isPasswordValid(password, user.getUserPassword(), user.getUserSalt())) {
                    return new UserResponse();
                }
            }
        }
        return new UserResponse("用户密码错误或者是用户不存在");
    }

    @Override
    public UserResponse register(UserVo userVo) {
        try {
            UserEntity entity = new UserEntity();
            BeanUtils.copyProperties(userVo, entity);
            entity.setUserType(UserTypeEnum.NORMAL);
            entity.setUserSalt(generateSalt(32));
            entity.setUserPassword(encodePassword(userVo.getUserPassword(), entity.getUserSalt()));
            userRepository.add(entity);
            return new UserResponse(entity.getId());
        } catch (DataIntegrityViolationException ex) {
            LOGGER.warn("", ex);
            return new UserResponse("用户注册失败, Email 或者 登录名已经存在.");
        } catch (Exception e) {
            LOGGER.warn("", e);
            return new UserResponse("用户注册超时, 请重试.");
        }
    }

    @Override
    public boolean activeUser(Long userId) {
        if (userId != null) {
            UserEntity user = userRepository.queryOne(userId);
            if (user.getUserStatus() == AWAIT) {
                userRepository.activeNewUser(userId);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkExistUser(String userIdentity, String userEmail) {
        if (isBlank(userIdentity) && isBlank(userEmail)) {
            return false;
        }
        return isNotEmpty(queryUserByIdentityOrEmail(userIdentity, userEmail));
    }

    @Override
    public UserVo queryUserById(Long userId) {
        UserEntity entity = userRepository.queryOne(checkNotNull(userId, "用户ID"));
        UserVo user = new UserVo();
        BeanUtils.copyProperties(entity, user);
        return user;
    }

    private List<UserEntity> queryUserByIdentityOrEmail(String userIdentity, String userEmail) {
        UserEntity entity = new UserEntity();
        entity.setUserIdentity(userIdentity);
        entity.setUserEmail(userEmail);
        return userRepository.queryExistUser(entity);
    }

    private void checkUserStatus(UserEntity user) {
        UserStatusEnum status = user.getUserStatus();
        if (status == UserStatusEnum.DELETED) {
            throw new IllegalStateException("用户不存在.");
        } else if (status == UserStatusEnum.FROZEN) {
            throw new IllegalStateException("用户已经被冻结, 请联系管理员.");
        } else if (status == UserStatusEnum.TEMP_FROZEN) {
            throw new IllegalStateException("用户被临时冻结.");
        }
    }
}
