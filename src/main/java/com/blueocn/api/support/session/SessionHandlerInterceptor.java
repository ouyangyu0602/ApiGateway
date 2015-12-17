/*
 * Copyright (c) 2008, 2015, OneAPM and/or its affiliates. All rights reserved.
 */
package com.blueocn.api.support.session;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.blueocn.api.support.Constants.LOGIN_URI;

/**
 * Title: SessionHandlerInterceptor
 * Description: 登录拦截器, 对未登录用户控制跳转
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-17 21:47
 */
public class SessionHandlerInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        if (!LOGIN_URI.equals(request.getRequestURI())) {
            if (SessionManager.INSTANCE.isNotLogin(request.getSession())) {
                response.sendRedirect(LOGIN_URI);
            }
        }
        return true;
    }
}
