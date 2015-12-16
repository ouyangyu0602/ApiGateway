/*
 * Copyright (c) 2008, 2015, OneAPM and/or its affiliates. All rights reserved.
 */
package com.blueocn.api.support.csrf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * CSRF Token控制器, 获取Session和请求中的Token
 * 如果Session中Token不存在, 表明是第一次请求, 附加上Token参数
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-15 12:54
 */
public final class CSRFTokenManager {

    static final String CSRF_PARAM_NAME = "csrfToken";
    public final static String CSRF_TOKEN_FOR_SESSION_ATTR_NAME = CSRFTokenManager.class.getSimpleName() + ".token";

    private CSRFTokenManager() {
        // No Construct Function
    }

    public static String getToken(HttpSession session) {
        String token = null;
        synchronized (session) {
            token = (String) session.getAttribute(CSRF_TOKEN_FOR_SESSION_ATTR_NAME);
            if (null == token) {
                token = UUID.randomUUID().toString();
                session.setAttribute(CSRF_TOKEN_FOR_SESSION_ATTR_NAME, token);
            }
        }
        return token;
    }

    public static String getToken(HttpServletRequest request) {
        String token = request.getParameter(CSRF_PARAM_NAME);
        if (token == null || "".equals(token)) {
            token = request.getHeader(CSRF_PARAM_NAME);
        }
        return token;
    }
}
