package com.blueocn.api.controller.ui.admin;

import com.blueocn.api.controller.ui.AbstractUIController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Title: PluginController
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-03-02 18:48
 */
@Controller
@RequestMapping("admin/plugin")
public class PluginController extends AbstractUIController {

    @RequestMapping(value = "select", method = RequestMethod.GET)
    public String addPlugin() {
        return "admin/plugin/select";
    }
}
