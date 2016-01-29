/*
 * Copyright (c) 2008, 2015, OneAPM and/or its affiliates. All rights reserved.
 */
package com.blueocn.api.support.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Title: MySQL 数据源配置
 * Description: 使用 druid, 其相关的配置使用 druid 开头即可
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-16 11:01
 */
@Configuration
public class DruidConfig {

    @Value("${druid.jdbc.url}")
    private String jdbcUrl;

    @Value("${druid.jdbc.username}")
    private String jdbcUsername;

    @Value("${druid.jdbc.password}")
    private String jdbcPassword;

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(jdbcUsername);
        dataSource.setPassword(jdbcPassword);

        return dataSource;
    }
}
