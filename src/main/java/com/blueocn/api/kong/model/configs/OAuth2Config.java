package com.blueocn.api.kong.model.configs;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

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
public class OAuth2Config implements Serializable {
    private static final long serialVersionUID = -2469455037787198356L;

    @JSONField(name = "scopes")
    private String[] scopes;

    @JSONField(name = "mandatory_scope")
    private Boolean mandatoryScope;

    @JSONField(name = "token_expiration")
    private Integer tokenExpiration;

    @JSONField(name = "enable_authorization_code")
    private Boolean enableAuthorizationCode;

    @JSONField(name = "enable_client_credentials")
    private Boolean enableClientCredentials;

    @JSONField(name = "enable_implicit_grant")
    private Boolean enableImplicitGrant;

    @JSONField(name = "enable_password_grant")
    private Boolean enablePasswordGrant;

    @JSONField(name = "hide_credentials")
    private Boolean hideCredentials;

    @JSONField(name = "accept_http_if_already_terminated")
    private Boolean acceptHttpIfAlreadyTerminated;

    @JSONField(name = "provision_key")
    private String provisionKey;
}
