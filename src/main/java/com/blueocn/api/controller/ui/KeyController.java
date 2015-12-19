/*
 * Copyright (c) 2008, 2015, OneAPM and/or its affiliates. All rights reserved.
 */
package com.blueocn.api.controller.ui;

import com.blueocn.api.kong.CustomerClient;
import com.blueocn.api.kong.data.CustomerKeyData;
import com.blueocn.api.support.session.SessionManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Title: KeyController
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-18 10:54
 */
@Controller
public class KeyController extends BaseController {

    @Resource
    private CustomerClient customerClient;

    @RequestMapping(value = "/user/keyList", method = RequestMethod.GET)
    public String keyApply(Model model,HttpServletRequest request) {
        List<CustomerKeyData> keyList = customerClient.getKeyList(getLoginUser(request).getUserIdentity());
        setPageTitle(model, "用户密钥列表");
        model.addAttribute("keyList", keyList);
        return "keyList";
    }

    @RequestMapping(value = "/user/deleteKey/{keyId}", method = RequestMethod.GET)
    public String deleteKey(@PathVariable("keyId") String keyId, HttpServletRequest request) {
        customerClient.deleteKey(SessionManager.INSTANCE.getLoginUser(request.getSession()).getUserIdentity(),
            keyId);
        return "redirect:/user/keyList";
    }
}
