package com.blueocn.api.response;

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
    private boolean success;

    /**
     * 响应信息
     */
    private String response;

    /**
     * 注册成功的用户ID
     */
    private Long userId;

    public UserResponse() {
        this.success = true;
    }

    public UserResponse(String response) {
        this.success = false;
        this.response = response;
    }

    public UserResponse(Long userId) {
        this.success = true;
        this.userId = userId;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
