/*
 * Copyright (c) 2008, 2015, OneAPM and/or its affiliates. All rights reserved. 
 */
package com.blueocn.api.repository.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Title: VersionEntity
 * Description: 版本化领域模型基类
 * 可基于此实现乐观锁概念
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-17 04:03
 */
@Setter
@Getter
public abstract class VersionEntity extends PersistenceEntity {
    private static final long serialVersionUID = 1263311144253791913L;

    private Long version = 0L;

    /*
     * 最后修改时间时间
     */
    private Date lastModifiedDate;
}
