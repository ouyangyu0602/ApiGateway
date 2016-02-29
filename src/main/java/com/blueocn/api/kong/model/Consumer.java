package com.blueocn.api.kong.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;

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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Consumer extends BaseModel {
    private static final long serialVersionUID = -3640559484596681040L;

    @JSONField(name = "id")
    private String id;

    @JSONField(name = "username")
    private String username;

    @JSONField(name = "custom_id")
    private String customId;

    @JSONField(name = "created_at")
    private Long createdAt;

    @JSONField(name = "size")
    private Integer size;

    @JSONField(name = "offset")
    private String offset;

    @JSONField(serialize = false, deserialize = false)
    private String errorMessage;
}
