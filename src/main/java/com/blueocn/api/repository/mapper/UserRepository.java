/*
 * Copyright (c) 2008, 2015, OneAPM and/or its affiliates. All rights reserved. 
 */
package com.blueocn.api.repository.mapper;

import com.blueocn.api.repository.domain.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Title: UserRepository
 * Description: 用户信息 Dao层操作
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-16 18:21
 */
@Repository
public interface UserRepository {

    Long add(UserEntity entity);

    void delete(Long id);

    void update(UserEntity entity);

    List<UserEntity> query(UserEntity entity);

    UserEntity queryOne(Long id);
}
