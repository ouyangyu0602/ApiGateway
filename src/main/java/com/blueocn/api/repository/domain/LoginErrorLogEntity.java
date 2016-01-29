package com.blueocn.api.repository.domain;

import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * Title: LoginErrorLogEntity
 * Description: 用户登录错误记录表实体
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-01-04 21:47
 */
@Alias("LoginErrorLogEntity")
public class LoginErrorLogEntity extends IdEntity {
    private static final long serialVersionUID = -7493933365217200766L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 错误时间
     */
    private Date errorTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getErrorTime() {
        return errorTime;
    }

    public void setErrorTime(Date errorTime) {
        this.errorTime = errorTime;
    }
}
