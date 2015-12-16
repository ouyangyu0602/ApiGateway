/*
 * Copyright (c) 2008, 2015, OneAPM and/or its affiliates. All rights reserved. 
 */
package com.blueocn.api.support;

import java.util.regex.Pattern;

/**
 * Title: Constants
 * Description: 基础常量类
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-16 10:53
 */
public interface Constants {

    // 逗号分割符
    Pattern COMMA_SPLIT_PATTERN = Pattern.compile("\\s*[,]+\\s*");
}
