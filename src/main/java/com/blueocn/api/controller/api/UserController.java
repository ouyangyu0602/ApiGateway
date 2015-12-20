/*
 * Copyright (c) 2008, 2015, OneAPM and/or its affiliates. All rights reserved.
 */
package com.blueocn.api.controller.api;

import com.blueocn.api.service.UserService;
import com.blueocn.api.support.session.SessionManager;
import com.blueocn.api.vo.RestfulResponse;
import com.blueocn.api.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Title: UserController
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-17 22:43
 */
@RestController
public class UserController extends AbstractResponseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public RestfulResponse login(UserVo userVo, HttpServletRequest request) {
        try {
            if (userService.login(checkNotNull(userVo.getUserIdentity(), "用户登录名"),
                checkNotNull(userVo.getUserPassword(), "用户密码"))) {
                SessionManager.INSTANCE.login(userVo, request.getSession());
                return new RestfulResponse();
            }
        } catch (Exception e) {
            LOGGER.warn("", e);
            return new RestfulResponse(e.getMessage());
        }
        return new RestfulResponse("登录失败, 用户信息不对.");
    }
}
