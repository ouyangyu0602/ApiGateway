/*
 * Copyright (c) 2008, 2016, OneAPM and/or its affiliates. All rights reserved.
 */
package com.blueocn.api.response;

import java.io.Serializable;

/**
 * Title: ValidateResponse
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-01-15 00:00
 */
public class ValidateResponse implements Serializable {

    private static final long serialVersionUID = 3011272701152734956L;

    /**
     * 响应结果状态, 标记成功失败
     */
    private boolean success;

    /**
     * 验证码对应的用户ID
     */
    private Long userId;

    public ValidateResponse() {
        this.success = false;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
