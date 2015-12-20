/*
 * Copyright (c) 2008, 2015, OneAPM and/or its affiliates. All rights reserved.
 */
package com.blueocn.api.controller.api;

import com.blueocn.api.kong.CustomerClient;
import com.blueocn.api.vo.RestfulResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Title: ApiController
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-18 10:54
 */
@RestController
public class ApiController extends AbstractResponseController {

    @Resource
    private CustomerClient customerClient;

    @RequestMapping(value = "user/applyKey", method = RequestMethod.POST)
    public RestfulResponse apiApply(String userId) {
        customerClient.addKey(userId, RandomStringUtils.randomAlphabetic(32));
        return new RestfulResponse();
    }
}
