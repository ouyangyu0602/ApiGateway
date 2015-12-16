/*
 * Copyright (c) 2008, 2015, OneAPM and/or its affiliates. All rights reserved.
 */
package com.blueocn.api.repository.domain;

/**
 * Title: KeyStoreEntity
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-17 03:57
 */
public class KeyStoreEntity extends PersistenceEntity {
    private static final long serialVersionUID = 5412799030856027766L;

    private Long userId;

    private String keyType;

    private String keyValue;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getKeyType() {
        return keyType;
    }

    public void setKeyType(String keyType) {
        this.keyType = keyType;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }
}
