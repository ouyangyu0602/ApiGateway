/*
 * Copyright (c) 2008, 2015, OneAPM and/or its affiliates. All rights reserved.
 */
package com.blueocn.api.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Title: 请求结果响应实体
 * Description: Kong的交互结果多为JSON, 需要针对性的封装, 其次, 自身部分功能设计时的请求封装
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-15 16:31
 */
public class RestfulResponse implements Serializable {

	private static final long serialVersionUID = -5795089018013798231L;

	private boolean success;

    private String code;

    private String msg;
    /**
     * total recorded
     */
    private int results;
    /**
     * rows
     */
    private List<?> rows;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getResults() {
        return results;
    }

    public void setResults(int results) {
        this.results = results;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
