package com.blueocn.api.service.impl;

import com.blueocn.api.service.MatrixService;
import com.blueocn.user.entity.ResultMessage;
import com.blueocn.user.entity.UserInfo;
import com.blueocn.user.impl.LoginService;
import com.blueocn.user.impl.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Title: MatrixServiceImpl
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-03-04 17:21
 */
@Service
public class MatrixServiceImpl implements MatrixService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MatrixServiceImpl.class);

    @Value("${matrix.url}")
    private String userSystemBaseUrl;

    private LoginService loginService;

    private UserInfoService userInfoService;

    @PostConstruct
    public void init() {
        loginService = new LoginService(userSystemBaseUrl);
        userInfoService = new UserInfoService(userSystemBaseUrl);
    }

    @Override
    public UserInfo login(String account, String password) {
        ResultMessage<String> loginResult = loginService.login(account, password);
        if (loginResult.success) {
            return getLoginUserInfo(loginResult.result);
        }
        LOGGER.warn("登录失败, 原因 {}", loginResult.message);
        return null;
    }

    private UserInfo getLoginUserInfo(String sessionId) {
        ResultMessage<UserInfo> userInfo = userInfoService.getUserInfo(sessionId);
        if (userInfo.success) {
            return userInfo.result;
        }
        LOGGER.warn("获取登录的用户信息失败, 原因 {}", userInfo.message);
        return null;
    }
}
