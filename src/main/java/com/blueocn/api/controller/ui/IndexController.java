/*
 * Copyright (c) 2008, 2015, OneAPM and/or its affiliates. All rights reserved.
 */
package com.blueocn.api.controller.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Title: UIController
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-16 17:57
 */
@Controller
public class IndexController extends AbstractUIController {

    @RequestMapping("/")
    public String index() {
        return "redirect:/index";
    }

    @RequestMapping("index")
    public String home(Model model) {
        setPageTitle(model, "首页");
        return "index";
    }
}
