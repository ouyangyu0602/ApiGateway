/*
 * Copyright (c) 2008, 2015, OneAPM and/or its affiliates. All rights reserved.
 */
package com.blueocn.api.support.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Title: RedisConfig
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-24 21:53
 */
@Configuration
public class RedisConfig {

    @Autowired
    private Config config;

    @Bean(name = "jedisConnectionFactory")
    public RedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(config.getRedisAddress());
        jedisConnectionFactory.setPort(config.getRedisPort());
        jedisConnectionFactory.setPassword(config.getRedisPassword());
        return jedisConnectionFactory;
    }

    @Bean(name = "redisTemplate")
    public RedisTemplate redisTemplate() {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }
}
