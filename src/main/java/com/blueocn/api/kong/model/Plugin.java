package com.blueocn.api.kong.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Title: Plugin
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-02-25 16:45
 */
@Setter
@Getter
public class Plugin<T extends Serializable> extends BaseModel {
    private static final long serialVersionUID = 4845313060910857062L;

    @JSONField(name = "id")
    private String id;

    /**
     * 插件的名称
     */
    @JSONField(name = "name")
    private String name;

    @JSONField(name = "api_id")
    private String apiId;

    @JSONField(name = "consumer_id")
    private String consumerId;

    @JSONField(name = "config")
    private T config;

    @JSONField(name = "enabled")
    private Boolean enabled;

    @JSONField(name = "created_at")
    private Long createdAt;

    @JSONField(name = "size")
    private Integer size;

    @JSONField(name = "offset")
    private String offset;

    @JSONField(serialize = false, deserialize = false)
    private String errorMessage;
}
