package com.blueocn.api.controller.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Title: IndexController
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-03-02 11:23
 */
@Controller
public class IndexController extends AbstractUIController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
