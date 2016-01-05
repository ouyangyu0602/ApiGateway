/*
 * Copyright (c) 2008, 2016, OneAPM and/or its affiliates. All rights reserved.
 */
package com.blueocn.api.controller.ui;

import com.blueocn.api.support.session.SessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Title: LoginController
 * Description: 用户登录控制器
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-01-04 22:05
 */
@Controller
public class LoginController extends AbstractUIController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(Model model) {
        setPageTitle(model, "登录");
        return "login";
    }

    @RequestMapping(value = "logout")
    public String logout(HttpServletRequest request) {
        SessionManager.INSTANCE.logout(request.getSession());
        return "redirect:/login";
    }
}
