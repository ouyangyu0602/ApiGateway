/*
 * Copyright (c) 2008, 2015, OneAPM and/or its affiliates. All rights reserved.
 */
package com.blueocn.api.support.spring;

import com.alibaba.fastjson.JSON;
import com.blueocn.api.support.AjaxUtils;
import com.blueocn.api.vo.RestfulResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Title: 异常处理视图类
 * Description: 用于处理交互异常, 并对Ajax请求和普通请求做了统一封装, 方便前端处理.
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-15 16:38
 */
@Component
public class ExceptionHandlerResolver implements HandlerExceptionResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        // Ajax
        if (AjaxUtils.isAjaxRequest(request)) {
            PrintWriter writer = null;
            try {
                writer = response.getWriter();
                RestfulResponse restfulResponse = new RestfulResponse();
                restfulResponse.setSuccess(false);
                StringWriter sw = new StringWriter();
                ex.printStackTrace(new PrintWriter(sw));
                restfulResponse.setMsg(sw.toString());
                writer.write(JSON.toJSONString(restfulResponse));
                writer.flush();
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        } else {
            LOGGER.error(ex.getMessage(), ex);
//            此种错误暂时无需在页面显示
//            request.setAttribute("message", ex.getMessage());
//            return new ModelAndView("common/error");
        }
        return null;
    }
}

