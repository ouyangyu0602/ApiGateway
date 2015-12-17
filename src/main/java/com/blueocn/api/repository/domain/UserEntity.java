/*
 * Copyright (c) 2008, 2015, OneAPM and/or its affiliates. All rights reserved. 
 */
package com.blueocn.api.repository.domain;

import com.blueocn.api.enums.UserTypeEnum;
import org.apache.ibatis.type.Alias;

/**
 * Title: UserEntity
 * Description: 用户表领域对象
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-17 03:27
 */
@Alias("UserEntity")
public class UserEntity extends IdEntity {
    private static final long serialVersionUID = 6044707143718263105L;

    private String userName;

    private String userIdentity;

    private String userPassword;

    private UserTypeEnum userType;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(String userIdentity) {
        this.userIdentity = userIdentity;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public UserTypeEnum getUserType() {
        return userType;
    }

    public void setUserType(UserTypeEnum userType) {
        this.userType = userType;
    }
}
