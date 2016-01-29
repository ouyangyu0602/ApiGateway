package com.blueocn.api.response;

import java.io.Serializable;
import java.util.List;

/**
 * Title: 请求结果响应实体
 * Description: 通用响应实体, 基于它扩展自己的属性
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-15 16:31
 */
public class RestfulResponse<T> implements Serializable {

	private static final long serialVersionUID = -5795089018013798231L;

	private boolean success;

    private String code;

    private T msg;
    /**
     * total recorded
     */
    private int results;
    /**
     * rows
     */
    private List<?> rows;

    public RestfulResponse() {
        this.success = true;
    }

    public RestfulResponse(T msg) {
        this.success = false;
        this.msg = msg;
    }

    public RestfulResponse(Builder<T> builder) {
        this.success = builder.success;
        this.code = builder.code;
        this.msg = builder.msg;
        this.results = builder.results;
        this.rows = builder.rows;
    }

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

    public T getMsg() {
        return msg;
    }

    public void setMsg(T msg) {
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

    public static class Builder<T> {
        private boolean success;
        private String code;
        private T msg;
        private int results;
        private List<?> rows;

        public Builder setSuccess(boolean success) {
            this.success = success;
            return this;
        }

        public Builder setCode(String code) {
            this.code = code;
            return this;
        }

        public Builder setMsg(T msg) {
            this.msg = msg;
            return this;
        }

        public Builder setResults(int results) {
            this.results = results;
            return this;
        }

        public Builder setRows(List<?> rows) {
            this.rows = rows;
            return this;
        }

        public RestfulResponse<T> build() {
            return new RestfulResponse<>(this);
        }
    }
}
