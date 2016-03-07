package com.blueocn.api.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Title: 请求结果响应实体
 * Description: 简单消息响应实体, 基于它扩展自己的属性
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-15 16:31
 */
@Setter
@Getter
public class RestfulResponse implements Serializable {
    private static final long serialVersionUID = -5795089018013798231L;

    private boolean success;

    private String code;

    private String msg;

    public RestfulResponse() {
        this.success = true;
    }

    public RestfulResponse(String msg) {
        if (msg != null) {
            this.success = false;
            this.msg = msg;
        }
    }
}
