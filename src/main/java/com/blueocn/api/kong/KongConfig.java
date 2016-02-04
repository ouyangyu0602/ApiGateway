package com.blueocn.api.kong;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Title: KongConfig
 * Description: Kong 配置对象, 读取配置文件中的 Kong 的配置
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-02-04 17:51
 */
@Component
public class KongConfig {

    /**
     * Kong 连接地址
     */
    @Value("kong.admin.address")
    private String kongAddress;

    /**
     * Kong 内部管理 API 请求端口
     */
    @Value("kong.admin.port")
    private Integer kongAdminPort;

    /**
     * Kong 调用超时时间
     */
    @Value("kong.invoke.timeout")
    private Integer kongInvokeTimeoutMillis;

    /**
     * 获取 Kong 的 API 管理地址
     */
    public String getKongAdminUrl() {
        return kongAddress + ":" + kongAdminPort;
    }

    public Integer getTimeoutMillis() {
        return kongInvokeTimeoutMillis;
    }
}
