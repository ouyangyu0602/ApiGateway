/*
 * Copyright (c) 2008, 2015, OneAPM and/or its affiliates. All rights reserved.
 */
package com.blueocn.api.support.config;

import com.google.common.collect.Maps;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Title: 系统配置信息加载
 * Description: 对于一些可变配置, 不写死于代码中, 使用文件定义. 后续考虑使用远程配置中心.
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-15 17:41
 */
public class ConfigLoader {

    private static final Map<String, String> CONFIG = Maps.newHashMap();
    private static final String CONF_FILE = "api-config.cfg";

    private static AtomicBoolean load = new AtomicBoolean(false);

    public static void load(String confPath) {
        String path = confPath + "/" + CONF_FILE;
        try {
            if (load.compareAndSet(false, true)) {
                Properties conf = new Properties();
                InputStream is = ConfigLoader.class.getClassLoader().getResourceAsStream(path);
                conf.load(is);

                for (Map.Entry<Object, Object> entry : conf.entrySet()) {
                    String key = entry.getKey().toString();
                    String value = entry.getValue() == null ? null : entry.getValue().toString();
                    CONFIG.put(key, value);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Load config[" + path + "] error ", e);
        }
    }

    public static Map<String, String> allConfig() {
        return CONFIG;
    }

    public static String getProperty(String name) {
        return CONFIG.get(name);
    }

    public static String getProperty(String name, String defaultValue) {
        String returnValue = CONFIG.get(name);
        if (returnValue == null || isBlank(returnValue)) {
            returnValue = defaultValue;
        }
        return returnValue;
    }
}
