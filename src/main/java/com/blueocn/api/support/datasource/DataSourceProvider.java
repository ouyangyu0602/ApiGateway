/*
 * Copyright (c) 2008, 2015, OneAPM and/or its affiliates. All rights reserved.
 */
package com.blueocn.api.support.datasource;

import com.blueocn.api.support.config.Config;

import javax.sql.DataSource;

/**
 * Title: DataSourceProvider
 * Description: 数据源工厂提供类接口, 可扩展定义实现其他数据源调用
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-16 10:33
 */
public interface DataSourceProvider {

    String MYSQL = "mysql";

    /**
     * 根据配置获取数据源
     *
     * @param config 基础配置, 启动时从配置文件加载
     * @return {@code DataSource}
     */
    DataSource getDataSource(Config config);
}
