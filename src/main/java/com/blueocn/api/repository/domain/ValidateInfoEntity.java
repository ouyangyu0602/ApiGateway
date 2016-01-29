package com.blueocn.api.repository.domain;

import org.apache.ibatis.type.Alias;

/**
 * Title: ValidateInfoEntity
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-01-11 23:16
 */
@Alias("ValidateInfoEntity")
public class ValidateInfoEntity extends IdEntity {
    private static final long serialVersionUID = -5663319310577759986L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 验证码
     */
    private String validateCode;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }
}
