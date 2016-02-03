package com.blueocn.api.vo;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Title: ApiVo
 * Description: API信息页面对象, 参见 https://getkong.org/docs/0.6.x/admin-api/#request-body
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-01-29 14:15
 */
public class ApiVo implements Serializable {
    private static final long serialVersionUID = 9177479561761205922L;

    @JSONField(name = "name")
    private String name;

    @JSONField(name = "request_host")
    private String requestHost;

    @JSONField(name = "request_path")
    private String requestPath;

    @JSONField(name = "strip_request_path")
    private boolean stripRequestPath;

    @JSONField(name = "preserve_host")
    private boolean preserveHost;

    @JSONField(name = "upstream_url")
    private String upstreamUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRequestHost() {
        return requestHost;
    }

    public void setRequestHost(String requestHost) {
        this.requestHost = requestHost;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }

    public boolean isStripRequestPath() {
        return stripRequestPath;
    }

    public void setStripRequestPath(boolean stripRequestPath) {
        this.stripRequestPath = stripRequestPath;
    }

    public boolean isPreserveHost() {
        return preserveHost;
    }

    public void setPreserveHost(boolean preserveHost) {
        this.preserveHost = preserveHost;
    }

    public String getUpstreamUrl() {
        return upstreamUrl;
    }

    public void setUpstreamUrl(String upstreamUrl) {
        this.upstreamUrl = upstreamUrl;
    }
}
