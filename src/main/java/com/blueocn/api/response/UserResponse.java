package com.blueocn.api.response;

import com.blueocn.api.vo.UserVo;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Title: UserResponse
 * Description: 包装响应结果, 出现方法都需要对外暴露的情况.
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-23 00:07
 */
@Setter
@Getter
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
    private UserVo userVo;

    public UserResponse() {
        this.success = true;
    }

    public UserResponse(String response) {
        this.success = false;
        this.response = response;
    }

    public UserResponse(UserVo userVo) {
        this.success = true;
        this.userVo = userVo;
    }
}
