/*
 * Copyright (c) 2008, 2015, OneAPM and/or its affiliates. All rights reserved. 
 */
package com.blueocn.api.support.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Title: ConfigInitializeBean
 * Description:
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
