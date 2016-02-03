package com.blueocn.api.support.config;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import static com.blueocn.api.support.Constants.SYSTEM_CONF_PROPERTIES;

/**
 * Title: ApiSystemConfig
 * Description: 基础注解配置类, Spring的初始化从这里开始.
 * 导入外部配置基础注解类, 此配置类无配置信息, 仅定义扫包信息
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
@Import({DruidConfig.class, MyBatisConfig.class, RedisConfig.class})
public class ApiSystemConfig { // NOSONAR

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocation(new ClassPathResource(SYSTEM_CONF_PROPERTIES));
        return configurer;
    }
}
