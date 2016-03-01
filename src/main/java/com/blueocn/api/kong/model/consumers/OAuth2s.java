package com.blueocn.api.kong.model.consumers;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.List;

/**
 * Title: OAuth2s
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-03-01 16:48
 */
public class OAuth2s implements Serializable {
    private static final long serialVersionUID = -5657126698304676264L;

    @JSONField(name = "total")
    private Integer total;

    @JSONField(name = "data")
    private List<OAuth2> data;

    @JSONField(name = "next")
    private String next;

}
