/*
 * Copyright (c) 2008, 2015, OneAPM and/or its affiliates. All rights reserved. 
 */
package com.blueocn.api.support.datasource;

import com.blueocn.api.support.config.Config;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Title: DataSourceProviderFactory
 * Description: 通用数据源初始化工厂类, 可扩展数据源配置
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-16 10:29
 */
public class DataSourceProviderFactory {

    private static final ConcurrentHashMap<String, DataSourceProvider> PROVIDER_MAP = new ConcurrentHashMap<String, DataSourceProvider>();

    static {
        PROVIDER_MAP.put(DataSourceProvider.MYSQL, new MysqlDataSourceProvider());
    }

    public static DataSourceProvider create(Config config) {
        String provider = config.getParameter("jdbc.datasource.provider", DataSourceProvider.MYSQL);
        DataSourceProvider dataSourceProvider = PROVIDER_MAP.get(provider);
        if (dataSourceProvider == null) {
            throw new IllegalArgumentException("Can not find jdbc.datasource.provider:" + provider);
        }
        return dataSourceProvider;
    }
}
