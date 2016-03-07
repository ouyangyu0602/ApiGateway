package com.blueocn.api.kong.model;

import lombok.*;

/**
 * Title: Api
 * Description: Kong API 请求信息实体, 参见 https://getkong.org/docs/0.6.x/admin-api/#request-body
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-01-29 14:15
 */
@Setter
@Getter
public class Api extends BaseModel {
    private static final long serialVersionUID = 9177479561761205922L;

    private String id;

    private String name;

    private String request_host;

    private String request_path;

    private Boolean strip_request_path;

    private Boolean preserve_host;

    private String upstream_url;

    private Long created_at;

    private Integer size;

    private String offset;
}
