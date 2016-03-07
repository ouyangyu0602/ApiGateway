package com.blueocn.api.service.impl;

import com.blueocn.api.service.MatrixService;
import com.blueocn.user.entity.ResultMessage;
import com.blueocn.user.entity.UserInfo;
import com.blueocn.user.impl.LoginService;
import com.blueocn.user.impl.UserInfoService;
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
    public ResultMessage<String> login(String account, String password) {
        return loginService.login(account, password);
    }

    public ResultMessage<UserInfo> getLoginUserInfo(String sessionId) {
        return userInfoService.getUserInfo(sessionId);
    }
}
