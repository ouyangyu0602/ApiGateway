/*
 * Copyright (c) 2008, 2015, OneAPM and/or its affiliates. All rights reserved. 
 */
package com.blueocn.api.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Title: UserVo
 * Description: 用户页面对象实体
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-17 21:40
 */
@Setter
@Getter
public class UserVo implements Serializable {
    private static final long serialVersionUID = 2252192347249740853L;

    private String userName;

    private String userIdentity;

    private String userPassword;
}
