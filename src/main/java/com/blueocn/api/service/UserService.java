/*
 * Copyright (c) 2008, 2015, OneAPM and/or its affiliates. All rights reserved.
 */
package com.blueocn.api.service;

/**
 * Title: UserService
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-17 22:50
 */
public interface UserService {

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 用户密码
     * @return 密码校验结果
     */
    boolean login(String username, String password);
}
