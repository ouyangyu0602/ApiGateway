package com.blueocn.api.support.session;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.blueocn.api.support.Constants.LOGIN_URI;
import static com.blueocn.api.support.Constants.LOGIN_USER;
import static com.blueocn.api.support.session.SessionManager.INSTANCE;

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

        if (INSTANCE.isNotLogin(request.getSession())) {
            response.sendRedirect(LOGIN_URI);
            return false;
        }
        request.setAttribute(LOGIN_USER, INSTANCE.getLoginUser(request.getSession()));
        return true;
    }
}
