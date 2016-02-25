package com.blueocn.api.kong.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Title: Customers
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-02-16 13:25
 */
@Setter
@Getter
public class Consumers implements Serializable {
    private static final long serialVersionUID = 4482965313444247622L;

    @JSONField(name = "total")
    private Integer total;

    @JSONField(name = "data")
    private List<Consumer> data;

    @JSONField(name = "next")
    private String next;
}
