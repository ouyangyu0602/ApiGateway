package com.blueocn.api.support.exception;

/**
 * Title: ExceptionCodeEnum
 * Description: 异常码集中定义处, 便于后面修改管理.
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-01-29 18:10
 */
public enum ExceptionCode {

    ;

    /**
     * 异常码定义
     */
    private String code;

    ExceptionCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
