package com.blueocn.api.support.session;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.blueocn.api.support.Constants.LOGIN_URI;
import static com.blueocn.api.support.Constants.LOGIN_USER;
import static com.blueocn.api.support.session.SessionManager.INSTANCE;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Title: SessionHandlerInterceptor
 * Description: 登录拦截器, 对未登录用户控制跳转
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-17 21:47
 */
public class SessionHandlerInterceptor extends HandlerInterceptorAdapter {

    @Value("${login.exclude.uri}")
    private String[] excludeUris;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        if (isIncludeUri(request.getRequestURI())) {
            if (INSTANCE.isNotLogin(request.getSession())) {
                response.sendRedirect(LOGIN_URI);
                return false;
            }
            request.setAttribute(LOGIN_USER, INSTANCE.getLoginUser(request.getSession()));
        }
        return true;
    }

    /**
     * 判断当前请求是否在待排除的 URI 列表内
     *
     * @param uri 请求 URI
     * @return 是否为 无需校验的URI
     */
    private boolean isExcludeUri(String uri) {
        if (excludeUris != null && excludeUris.length > 0) {
            for (String excludeUri : excludeUris) {
                if (isNotBlank(excludeUri) && excludeUri.trim().equals(uri)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isIncludeUri(String uri) {
        return !isExcludeUri(uri);
    }

    // Useless method, just for findbugs fix.
    public void setExcludeUris(String[] excludeUris) {
        this.excludeUris = excludeUris;
    }
}
