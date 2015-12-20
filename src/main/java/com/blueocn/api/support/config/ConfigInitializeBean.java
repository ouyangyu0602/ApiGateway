/*
 * Copyright (c) 2008, 2015, OneAPM and/or its affiliates. All rights reserved. 
 */
package com.blueocn.api.support.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Title: ConfigInitializeBean
 * Description: 对与Config配置实体类的初始化配置Bean, 调用Config的工厂类, 构建需要的 Config 单例Bean.
 * 如果使用XML的形式定义, 这边会更加优雅.
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-16 16:35
 */
@Configuration
public class ConfigInitializeBean {

    @Bean(name = "config")
    public Config getConfig() {
        ConfigFactoryBean configFactoryBean = new ConfigFactoryBean();
        configFactoryBean.afterPropertiesSet();
        return configFactoryBean.getObject();
    }
}
