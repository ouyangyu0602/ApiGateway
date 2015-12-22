/*
 * Copyright (c) 2008, 2015, OneAPM and/or its affiliates. All rights reserved.
 */
package com.blueocn.api.support.config;

import com.alibaba.fastjson.JSON;
import com.blueocn.api.support.Constants;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Map;

/**
 * Title: 配置实体
 * Description: 对可配置属性的实际定义, 包装于此, 方便调用和统一管理
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-16 10:40
 */
@Component("config")
public class Config implements Serializable {
    private static final long serialVersionUID = 3694442039402766466L;

    // API 中心地址
    private String kongAddress;

    // 远程连接超时时间
    private int invokeTimeoutMillis;

    // API 中心管理端口
    private int listenPort;

    // 配置文件中以 config. 开头的的配置参数, 实际的 key 会去除 config.
    private final Map<String, String> parameters = Maps.newHashMap();

    @PostConstruct
    public void initConfig() {
        String kongAddress = ConfigLoader.getProperty("kongAddress");
        if (StringUtils.isBlank(kongAddress)) {
            throw new IllegalArgumentException("kongAddress in api-config.cfg can't be null.");
        }
        this.setKongAddress(kongAddress);
        this.setListenPort(Integer.parseInt(ConfigLoader.getProperty("listenPort")));
        this.setInvokeTimeoutMillis(Integer.parseInt(ConfigLoader.getProperty("invokeTimeoutMillis")));

        for (Map.Entry<String, String> entry : ConfigLoader.allConfig().entrySet()) {
            // 将 config. 开头的配置都加入到config中
            if (entry.getKey().startsWith("configs.")) {
                this.setParameter(entry.getKey().replaceFirst("configs.", ""), entry.getValue());
            }
        }
    }

    public String getKongAddress() {
        return kongAddress;
    }

    public void setKongAddress(String kongAddress) {
        this.kongAddress = kongAddress;
    }

    public int getInvokeTimeoutMillis() {
        return invokeTimeoutMillis;
    }

    public void setInvokeTimeoutMillis(int invokeTimeoutMillis) {
        this.invokeTimeoutMillis = invokeTimeoutMillis;
    }

    public int getListenPort() {
        return listenPort;
    }

    public void setListenPort(int listenPort) {
        this.listenPort = listenPort;
    }

    public void setParameter(String key, String value) {
        parameters.put(key, value);
    }

    /**
     * 获取所有的可选配置参数
     */
    public Map<String, String> getParameters() {
        return parameters;
    }

    /**
     * 获取指定 config.key 的配置参数
     */
    public String getParameter(String key) {
        return parameters.get(key);
    }

    /**
     * 获取指定 config.key 的配置参数
     *
     * @param key          配置键
     * @param defaultValue 此配置不存在时返回的默认值
     */
    public String getParameter(String key, String defaultValue) {
        String value = parameters.get(key);
        if (value == null) {
            return defaultValue;
        }
        return value;
    }

    /**
     * 获取 config.key 对应的布尔值
     *
     * @param key          配置键
     * @param defaultValue 此配置不存在时返回的布尔值
     */
    public boolean getParameter(String key, boolean defaultValue) {
        String value = getParameter(key);
        if (value == null || value.length() == 0) {
            return defaultValue;
        }
        return Boolean.parseBoolean(value);
    }

    /**
     * 配置文件中允许定义数组, 格式为逗号间隔
     *
     * @param key          配置键
     * @param defaultValue 此配置不存在时返回的默认数组
     */
    public String[] getParameter(String key, String[] defaultValue) {
        String value = getParameter(key);
        if (value == null || value.length() == 0) {
            return defaultValue;
        }
        return Constants.COMMA_SPLIT_PATTERN.split(value);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
