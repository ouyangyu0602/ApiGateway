/*
 * Copyright (c) 2008, 2016, OneAPM and/or its affiliates. All rights reserved.
 */
package com.blueocn.api.enums;

/**
 * Title: UserStatusEnum
 * Description: 定义用户的状态, 用户的数据库表实体属性.
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-01-04 21:28
 */
public enum UserStatusEnum {

    AWAIT("待激活"),
    NORMAL("正常状态"),
    TEMP_FROZEN("临时冻结"),
    FROZEN("冻结"),
    DELETED("已删除");

    private String description;

    UserStatusEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
