/*
 * Copyright (c) 2008, 2016, OneAPM and/or its affiliates. All rights reserved.
 */
package com.blueocn.api.controller.ui;

import com.blueocn.api.service.UserService;
import com.blueocn.api.support.session.SessionManager;
import com.blueocn.api.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.blueocn.api.support.Constants.LOGIN_URI;
import static com.blueocn.api.support.utils.Assert.checkNotBlank;

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

    @Resource
    private UserService userService;

    @RequestMapping(value = LOGIN_URI, method = RequestMethod.GET)
    public String login(Model model) {
        setPageTitle(model, "登录");
        return "login";
    }

    @RequestMapping(value = LOGIN_URI, method = RequestMethod.POST)
    public String login(UserVo userVo, HttpServletRequest request, Model model) {
        try {
            if (userService.login(checkNotBlank(userVo.getUserIdentity(), "用户登录名不能为空"),
                checkNotBlank(userVo.getUserPassword(), "用户密码不能为空"))) {
                SessionManager.INSTANCE.login(userVo, request.getSession());
                return "redirect:/index";
            }
            setErrorMessage(model, "用户密码错误或者是用户不存在");
        } catch (Exception e) {
            LOGGER.warn("", e);
            setErrorMessage(model, e.getMessage());
        }
        setPageTitle(model, "登录");
        return "login";
    }

    @RequestMapping(value = "logout")
    public String logout(HttpServletRequest request) {
        SessionManager.INSTANCE.logout(request.getSession());
        return "redirect:" + LOGIN_URI;
    }
}
