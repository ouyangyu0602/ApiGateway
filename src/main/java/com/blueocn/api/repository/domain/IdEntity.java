/*
 * Copyright (c) 2008, 2015, OneAPM and/or its affiliates. All rights reserved. 
 */
package com.blueocn.api.repository.domain;

import java.io.Serializable;

/**
 * Title: IdEntity
 * Description: 领域模型基类
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-17 03:16
 */
public abstract class IdEntity implements Serializable {
    private static final long serialVersionUID = 8810780370104541317L;

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
