/*
 * Copyright (c) 2008, 2015, OneAPM and/or its affiliates. All rights reserved.
 */
package com.blueocn.api.controller.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Title: UIController
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-16 17:57
 */
@Controller
public class UIController extends BaseController {

    @RequestMapping("index")
    public String index(Model model) {
        setPageTitle(model, "首页");
        return "index";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(Model model) {
        setPageTitle(model, "登录");
        return "login";
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout() {
        return "login";
    }
}
