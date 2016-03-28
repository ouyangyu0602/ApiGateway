package com.blueocn.api.kong.model.consumers;

import com.blueocn.api.kong.model.BaseModel;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OAuth2 extends BaseModel {
    private static final long serialVersionUID = 2701274499207198826L;

    private String consumer_id;

    private String id;

    private String name;

    private String client_id;

    private String client_secret;

    private String redirect_uri;

    private Long created_at;

    private Integer size;
}
