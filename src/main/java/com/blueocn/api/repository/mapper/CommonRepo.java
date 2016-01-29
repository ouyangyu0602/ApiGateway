/*
 * Copyright (c) 2008, 2015, OneAPM and/or its affiliates. All rights reserved. 
 */
package com.blueocn.api.repository.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Title: CommonRepository
 * Description: 通用Dao层操作, 主要用于执行SQL代码, 一般情况不使用此方法.
 * 推荐用于系统初次上线和后续数据库变更的时候.
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-16 14:26
 */
@Repository
public interface CommonRepo {

    void executeSQL(@Param("sql") String sql);
}
