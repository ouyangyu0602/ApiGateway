package com.blueocn.api.kong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static org.apache.commons.lang3.StringUtils.isNumeric;

/**
 * Title: KongConfig
 * Description: Kong 配置对象, 读取配置文件中的 Kong 的配置, 生成最终调用的 Kong Admin URL.
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-02-04 17:51
 */
@Component
public class KongConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(KongConfig.class);

    /**
     * Kong 对应域名, 公网通过此域名访问 Kong
     */
    @Value("${kong.host}")
    private String kongHost;

    @Value("${kong.http.port}")
    private String kongHttpPort;

    @Value("${kong.https.port}")
    private String kongHttpsPort;

    @Value("${kong.admin.address}")
    private String kongAdminAddress;

    /**
     * Kong 内部管理 API 请求端口, 本来该用Integer, 但是用它的话,
     * 配置文件就必须指定值, 实际很多时候应该有缺省值, 所以这里改成 String
     */
    @Value("${kong.admin.port}")
    private String kongAdminPort;

    /**
     * Kong 调用超时时间
     */
    @Value("${kong.invoke.timeout}")
    private Integer kongInvokeTimeoutMillis;

    private String getKongHttpPort() {
        if (kongHttpPort != null) {
            if (isNumeric(kongHttpPort)) {
                return kongHttpPort;
            } else {
                LOGGER.warn("Kong Http 访问端口 [{}] 配置错误, 使用缺省端口 [8000]", kongHttpPort);
            }
        }
        return "8000";
    }

    private String getKongHttpsPort() {
        if (kongHttpsPort != null) {
            if (isNumeric(kongHttpsPort)) {
                return kongHttpsPort;
            } else {
                LOGGER.warn("Kong Https 访问端口 [{}] 配置错误, 使用缺省端口 [8443]", kongHttpsPort);
            }
        }
        return "8443";
    }

    private String getKongAdminAddress() {
        if (kongAdminAddress != null) {
            // Kong 管理页面默认无 HTTPS, 实际使用时位于内网, 对外不公开, 所以使用 HTTP.
            return kongAdminAddress.startsWith("http") ? kongAdminAddress : "http://" + kongAdminAddress;
        }
        return "http://127.0.0.1"; // 缺省配置 NOSONAR
    }

    private String getKongAdminPort() {
        if (kongAdminPort != null) {
            if (isNumeric(kongAdminPort)) {
                return kongAdminPort;
            } else {
                LOGGER.warn("Kong 的管理端口 [{}] 配置错误, 实际参数必须是数字, 所以使用缺省端口 [8001]", kongAdminPort);
            }
        }
        return "8001";
    }

    public String getKongHost() {
        return kongHost == null ? "localhost" : kongHost;
    }

    public String getKongHttpAddress() {
        return "http://" + getKongHost() + ":" + getKongHttpPort();
    }

    public String getKongHttpsAddress() {
        return "https://" + getKongHost() + ":" + getKongHttpsPort();
    }

    /**
     * 获取 Kong 的 API 管理地址
     * 不带 "/" 结尾, Retrofit2 调用问题, 相对路径.
     */
    public String getKongAdminUrl() {
        return getKongAdminAddress() + ":" + getKongAdminPort();
    }

    public Integer getTimeoutMillis() {
        return kongInvokeTimeoutMillis;
    }
}
