package com.blueocn.api.kong.model.configs;

import com.blueocn.api.kong.model.BaseModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * Title: OAuth2
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-02-29 10:29
 */
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OAuth2Config extends BaseModel {
    private static final long serialVersionUID = -2469455037787198356L;

    private String[] scopes;

    private Boolean mandatory_scope;

    private Integer token_expiration;

    private Boolean enable_authorization_code;

    private Boolean enable_client_credentials;

    private Boolean enable_implicit_grant;

    private Boolean enable_password_grant;

    private Boolean hide_credentials;

    private Boolean accept_http_if_already_terminated;

    private String provision_key;
}
