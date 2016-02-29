package com.blueocn.api.kong.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Title: Plugins
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-02-25 16:45
 */
@Getter
@Setter
public class Plugins<T extends Serializable> {

    @JSONField(name = "total")
    private Integer total;

    @JSONField(name = "data")
    private List<Plugin<T>> data;

    @JSONField(name = "next")
    private String next;
}
