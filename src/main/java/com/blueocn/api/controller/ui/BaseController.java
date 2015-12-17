/*
 * Copyright (c) 2008, 2015, OneAPM and/or its affiliates. All rights reserved.
 */
package com.blueocn.api.controller.ui;

import org.springframework.ui.Model;

import static com.blueocn.api.support.Constants.PAGE_TITLE;

/**
 * Title: UI控制器基础公共类
 * Description: 定义某些公共方法, 公共属性
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-15 15:54
 */
public abstract class BaseController {

    /**
     * 对于显示的页面标题定义方法
     *
     * @param model     {@code Model} Spring MVC 页面模型
     * @param pageTitle 想要设置的标题
     */
    protected void setPageTitle(Model model, String pageTitle) {
        model.addAttribute(PAGE_TITLE, " | " + pageTitle);
    }
}
