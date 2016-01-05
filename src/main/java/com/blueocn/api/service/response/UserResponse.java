/*
 * Copyright (c) 2008, 2015, OneAPM and/or its affiliates. All rights reserved.
 */
package com.blueocn.api.service.response;

import java.io.Serializable;

/**
 * Title: UserResponse
 * Description: 包装响应结果, 出现方法都需要对外暴露的情况.
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-23 00:07
 */
public class UserResponse implements Serializable {

    private static final long serialVersionUID = -8932495247335419789L;

    /**
     * 响应结果状态, 标记成功失败
     */
    public boolean success;

    /**
     * 响应信息
     */
    public String message;

    public UserResponse() {
        this.success = true;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
