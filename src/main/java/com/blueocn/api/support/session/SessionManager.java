/*
 * Copyright (c) 2008, 2015, OneAPM and/or its affiliates. All rights reserved. 
 */
package com.blueocn.api.support.session;

import com.blueocn.api.vo.UserVo;

import javax.servlet.http.HttpSession;

import static com.blueocn.api.support.Constants.LOGIN_USER_IN_SESSION;

/**
 * Title: SessionManager
 * Description: 用户 Session 管理
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-17 21:38
 */
public enum SessionManager {

    INSTANCE;

    public boolean isLogin(HttpSession session) {
        UserVo user = getLoginUser(session);
        return user != null;
    }

    public boolean isNotLogin(HttpSession session) {
        return !isLogin(session);
    }

    public UserVo getLoginUser(HttpSession session) {
        return (UserVo) session.getAttribute(LOGIN_USER_IN_SESSION);
    }

    public void login(UserVo user, HttpSession session) {
        session.setAttribute(LOGIN_USER_IN_SESSION, user);
    }

    public void logout(HttpSession session) {
        session.invalidate();
    }
}
