/*
 * Copyright (c) 2008, 2016, OneAPM and/or its affiliates. All rights reserved.
 */
package com.blueocn.api.repository.mapper;

import com.blueocn.api.repository.domain.LoginErrorLogEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Title: LoginErrorLogRepository
 * Description: 用户错误信息表操作 Dao
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-01-04 21:50
 */
@Repository
public interface LoginErrorLogRepository {

    Long add(LoginErrorLogEntity entity);

    void insert(List<LoginErrorLogEntity> list);

    void delete(Long id);

    void update(LoginErrorLogEntity entity);

    List<LoginErrorLogEntity> query(LoginErrorLogEntity entity);

    LoginErrorLogEntity queryOne(Long id);

}
