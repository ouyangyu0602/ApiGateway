/*
 * Copyright (c) 2008, 2015, OneAPM and/or its affiliates. All rights reserved.
 */
package com.blueocn.api.support.spring;

import com.blueocn.api.support.config.Config;
import com.blueocn.api.support.datasource.DataSourceProviderFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

/**
 * Title: 数据库源配置类
 * Description: 懒得配置复杂的数据源, 使用偷懒简单的配置类定义.
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-15 18:41
 */
public class DataSourceFactoryBean implements FactoryBean<DataSource>, InitializingBean, DisposableBean {

    private DataSource dataSource;

    @Autowired
    private Config config;

    @Override
    public DataSource getObject() throws Exception {
        return dataSource;
    }

    @Override
    public Class<?> getObjectType() {
        return DataSource.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        dataSource = DataSourceProviderFactory.create(config).getDataSource(config);
    }

    @Override
    public void destroy() throws Exception {

    }
}
