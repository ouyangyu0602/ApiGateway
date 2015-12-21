/*
 * Copyright (c) 2008, 2015, OneAPM and/or its affiliates. All rights reserved.
 */
package com.blueocn.api.support.config;

import com.blueocn.api.support.datasource.DataSourceProviderFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Title: ApiSystemConfig
 * Description: 基础注解配置类, Spring的初始化从这里开始.
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-20 19:36
 */
@Configuration
@ComponentScan(
    basePackages = {"com.blueocn.api"},
    excludeFilters = {
        @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.blueocn.api.controller.*"),
        @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.blueocn.api.support.config.WebConfig")
    }
)
@MapperScan(basePackages = "com.blueocn.api.repository.mapper", annotationClass = org.springframework.stereotype.Repository.class)
public class ApiSystemConfig implements ApplicationContextAware {

    private ApplicationContext context;

    @Autowired
    private Config config;

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        return DataSourceProviderFactory.create(config).getDataSource(config);
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setTypeAliasesPackage("com.blueocn.api.repository.domain");
        factoryBean.setMapperLocations(context.getResources("classpath:mybatis/mapper/*.xml"));
        return factoryBean.getObject();
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context = context;
    }
}
