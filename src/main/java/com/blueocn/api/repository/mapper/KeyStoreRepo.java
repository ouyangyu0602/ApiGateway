/*
 * Copyright (c) 2008, 2015, OneAPM and/or its affiliates. All rights reserved. 
 */
package com.blueocn.api.repository.mapper;

import com.blueocn.api.repository.domain.KeyStoreEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Title: KeyStoreRepository
 * Description: 用户API凭证 Dao层操作
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-17 03:04
 */
@Repository
public interface KeyStoreRepo {

    Long add(KeyStoreEntity entity);

    void insert(List<KeyStoreEntity> list);

    void delete(Long id);

    void update(KeyStoreEntity entity);

    List<KeyStoreEntity> query(KeyStoreEntity entity);

    KeyStoreEntity queryOne(Long id);
}
