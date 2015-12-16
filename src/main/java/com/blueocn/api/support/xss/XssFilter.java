/*
 * Copyright (c) 2008, 2015, OneAPM and/or its affiliates. All rights reserved.
 */
package com.blueocn.api.support.xss;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Title: Xss过滤器
 * Description: 防跨站处理
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-15 16:38
 */
public class XssFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        //
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest) request), response);
    }

    public void destroy() {
        //
    }
}
