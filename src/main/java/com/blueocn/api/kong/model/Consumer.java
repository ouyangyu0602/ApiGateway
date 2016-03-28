package com.blueocn.api.kong.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * Title: Customer
 * Description: Kong Customer 请求信息实体, 参见
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-02-16 13:20
 */
@Setter
@Getter
public class Consumer extends BaseModel {
    private static final long serialVersionUID = -3640559484596681040L;

    private String id;

    private String username;

    private String custom_id;

    private Long created_at;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer size;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String offset;
}
