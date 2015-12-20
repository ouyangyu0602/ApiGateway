/*
 * Copyright (c) 2008, 2015, OneAPM and/or its affiliates. All rights reserved. 
 */
package com.blueocn.api.initialization;

import com.blueocn.api.support.config.ConfigLoader;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Title: 系统初始化加载
 * Description: 读取配置信息中指定的配置文件运行路径, 调用ConfigureLoader初始化
 * 原来这么写的原因是想直接使用 Spring 的 profile 方式, 定义不同的加载路径初始化.
 * 后面觉得没必要那么麻烦, 所以这个设计被废弃, 此为废弃下来的代码.
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-16 13:15
 */
public class SystemInitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String confPath = servletContextEvent.getServletContext().getInitParameter("lts.admin.config.path");
        ConfigLoader.load(confPath);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Do nothing ...
    }
}
