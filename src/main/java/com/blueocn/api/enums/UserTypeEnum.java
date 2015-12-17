/*
 * Copyright (c) 2008, 2015, OneAPM and/or its affiliates. All rights reserved. 
 */
package com.blueocn.api.enums;

/**
 * Title: UserTypeEnum
 * Description: 用户类型枚举类
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-17 04:17
 */
public enum UserTypeEnum {

    NORMAL("普通用户"),
    ADMINISTRATOR("管理员");

    private String description;

    UserTypeEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
