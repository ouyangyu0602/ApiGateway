package com.blueocn.api.service;

import com.blueocn.user.entity.ResultMessage;
import com.blueocn.user.entity.UserInfo;

/**
 * Title: MatrixService
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-03-04 17:20
 */
public interface MatrixService {

    /**
     * 用户登录
     *
     * @param account  帐号名 (邮箱)
     * @param password 密码
     */
    ResultMessage<String> login(String account, String password);


    ResultMessage<UserInfo> getLoginUserInfo(String sessionId);
}
