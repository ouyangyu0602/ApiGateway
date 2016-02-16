package com.blueocn.api.kong.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;

import java.io.Serializable;

/**
 * Title: ApiReq
 * Description: Kong API 请求信息实体, 参见 https://getkong.org/docs/0.6.x/admin-api/#request-body
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-01-29 14:15
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiReq implements Serializable {
    private static final long serialVersionUID = 9177479561761205922L;

    @JSONField(name = "id")
    private String id;

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

    @JSONField(name = "created_at")
    private Long createdAt;

    @JSONField(name = "size")
    private Integer size;

    @JSONField(name = "offset")
    private String offset;
}
