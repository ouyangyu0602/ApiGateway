/*
 * Copyright (c) 2008, 2015, OneAPM and/or its affiliates. All rights reserved. 
 */
package com.blueocn.api.initialization;

import com.blueocn.api.support.config.AppConfigurer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Title: 系统初始化加载
 * Description: 读取配置信息初始化
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-16 13:15
 */
public class SystemInitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String confPath = servletContextEvent.getServletContext().getInitParameter("lts.admin.config.path");
        AppConfigurer.load(confPath);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Do nothing ...
    }
}
