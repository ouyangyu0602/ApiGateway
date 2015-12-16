/*
 * Copyright (c) 2008, 2015, OneAPM and/or its affiliates. All rights reserved. 
 */
package com.blueocn.api.support.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.blueocn.api.support.config.Config;
import com.google.common.base.Joiner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Title: MySQL 数据源配置
 * Description: 使用 druid, 其相关的配置使用 druid 开头即可
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-16 11:01
 */
public class MysqlDataSourceProvider implements DataSourceProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(MysqlDataSourceProvider.class);

    // 同一配置, 始终保持同一个连接
    private static final ConcurrentHashMap<String, DataSource> DATA_SOURCE_MAP = new ConcurrentHashMap<String, DataSource>();

    private static final Object lock = new Object();

    @Override
    public DataSource getDataSource(Config config) {
        String url = config.getParameter(URL_KEY);
        String username = config.getParameter(USERNAME_KEY);
        String password = config.getParameter(PASSWORD_KEY);

        String cachedKey = Joiner.on("").join(url, username, password);

        DataSource dataSource = DATA_SOURCE_MAP.get(cachedKey);

        if (dataSource == null) {
            try {
                synchronized (lock) {
                    dataSource = DATA_SOURCE_MAP.get(cachedKey);
                    if (dataSource != null) {
                        return dataSource;
                    }
                    dataSource = createDruidDataSource(config);

                    DATA_SOURCE_MAP.put(cachedKey, dataSource);
                }
            } catch (Exception e) {
                throw new IllegalStateException(
                    String.format("connect datasource failed! url: %s", url), e);
            }
        }
        return dataSource;
    }

    private DataSource createDruidDataSource(Config config) {
        DruidDataSource dataSource = new DruidDataSource();
        Class<DruidDataSource> clazz = DruidDataSource.class;
        for (Map.Entry<String, Class<?>> entry : FIELDS.entrySet()) {
            String field = entry.getKey();
            String value = config.getParameter("druid." + field);
            if (isNotBlank(value)) {
                Method setMethod = null;
                try {
                    setMethod = clazz.getMethod("set" + (field.substring(0, 1).toUpperCase() + field.substring(1)),
                        entry.getValue());
                    setMethod.invoke(dataSource, value);
                } catch (Exception e) {
                    LOGGER.warn("set field[{}] failed! value is {}", field, value);
                }
            }
        }

        String url = config.getParameter(URL_KEY);
        String username = config.getParameter(USERNAME_KEY);
        String password = config.getParameter(PASSWORD_KEY);

        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }

    /**
     * druid配置属性，see <a href="https://github.com/alibaba/druid/wiki/DruidDataSource%E9%85%8D%E7%BD%AE%E5%B1%9E%E6%80%A7%E5%88%97%E8%A1%A8">DruidDataSource配置属性列表</a>
     */
    private static final Map<String, Class<?>> FIELDS = new ConcurrentHashMap<String, Class<?>>();

    static {
//        FIELDS.put("url", String.class); // 数据库的基础信息单独配置, 因为会有扩展的需求
//        FIELDS.put("username", String.class);
//        FIELDS.put("password", String.class);
//        FIELDS.put("driverClassName", String.class); // 参考 druid 可以不配置
        FIELDS.put("initialSize", Integer.class);
        FIELDS.put("maxActive", Integer.class);
        FIELDS.put("maxIdle", Integer.class);
        FIELDS.put("minIdle", Integer.class);
        FIELDS.put("maxWait", Integer.class);
        FIELDS.put("poolPreparedStatements", Boolean.class);
        FIELDS.put("maxOpenPreparedStatements", Integer.class);
        FIELDS.put("validationQuery", String.class);
        FIELDS.put("testOnBorrow", Boolean.class);
        FIELDS.put("testOnReturn", Boolean.class);
        FIELDS.put("testWhileIdle", Boolean.class);
        FIELDS.put("timeBetweenEvictionRunsMillis", Long.class);
        FIELDS.put("numTestsPerEvictionRun", Integer.class);
        FIELDS.put("minEvictableIdleTimeMillis", Long.class);
        FIELDS.put("exceptionSorter", String.class);
        FIELDS.put("filters", String.class);
    }

    private static final String URL_KEY = "jdbc.url";
    private static final String USERNAME_KEY = "jdbc.username";
    private static final String PASSWORD_KEY = "jdbc.password";
}
