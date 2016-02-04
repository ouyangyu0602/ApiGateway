/*
 * Copyright (c) 2008, 2015, OneAPM and/or its affiliates. All rights reserved. 
 */
package com.blueocn.api.repository.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Title: PersistenceEntity
 * Description: 持久化领域模型基类
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-17 03:19
 */
@Getter
@Setter
public abstract class PersistenceEntity extends IdEntity {
    private static final long serialVersionUID = -7210028618007753467L;

    /*
     * 创建时间
     */
    private Date createdDate;
}
