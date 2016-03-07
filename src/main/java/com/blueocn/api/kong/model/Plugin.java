package com.blueocn.api.kong.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

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
public class Plugin extends BaseModel {
    private static final long serialVersionUID = 4845313060910857062L;

    @JSONField(name = "id")
    private String id;

    /**
     * 插件的名称
     */
    private String name;

    @JSONField(name = "api_id")
    private String api_id;

    private String consumer_id;

    private Map<String, Object> config;

    private Boolean enabled;

    private Long created_at;

    private Integer size;

    private String offset;
}
