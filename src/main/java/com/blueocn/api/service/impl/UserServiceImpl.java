/*
 * Copyright (c) 2008, 2015, OneAPM and/or its affiliates. All rights reserved.
 */
package com.blueocn.api.service.impl;

import com.blueocn.api.repository.domain.UserEntity;
import com.blueocn.api.repository.mapper.UserRepository;
import com.blueocn.api.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Title: UserServiceImpl
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-17 22:50
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    public boolean login(String username, String password) {
        // TODO 加密, 换Spring Security
        UserEntity entity = new UserEntity();
        entity.setUserIdentity(username);
        entity.setUserPassword(password);
        List<UserEntity> list = userRepository.query(entity);
        return CollectionUtils.isNotEmpty(list);
    }
}
