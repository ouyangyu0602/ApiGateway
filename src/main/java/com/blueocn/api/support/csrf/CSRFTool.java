/*
 * Copyright (c) 2008, 2015, OneAPM and/or its affiliates. All rights reserved.
 */
package com.blueocn.api.support.csrf;

import javax.servlet.http.HttpServletRequest;

/**
 * 模板工具, 配置在 velocity tools 中
 * <input type="hidden" name="csrfToken" value="$csrfTool.getToken($request)"/>
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-15 12:54
 */
public class CSRFTool {
    public static String getToken(HttpServletRequest request) {
        return CSRFTokenManager.getToken(request.getSession());
    }
}
