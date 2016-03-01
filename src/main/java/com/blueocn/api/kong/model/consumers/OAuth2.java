package com.blueocn.api.kong.model.consumers;

import com.alibaba.fastjson.annotation.JSONField;
import com.blueocn.api.kong.model.BaseModel;
import lombok.Getter;
import lombok.Setter;

/**
 * Title: OAuth2
 * Description: 用户获取的 OAuth2 对象
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-03-01 16:11
 */
@Setter
@Getter
public class OAuth2 extends BaseModel {
    private static final long serialVersionUID = 2701274499207198826L;

    @JSONField(name = "consumer_id")
    private String consumerId;

    @JSONField(name = "id")
    private String id;

    @JSONField(name = "name")
    private String name;

    @JSONField(name = "client_id")
    private String clientId;

    @JSONField(name = "client_secret")
    private String clientSecret;

    @JSONField(name = "redirect_uri")
    private String redirectUri;

    @JSONField(name = "created_at")
    private Long createdAt;
}
