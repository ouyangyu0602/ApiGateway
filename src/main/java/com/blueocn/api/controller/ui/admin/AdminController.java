package com.blueocn.api.controller.ui.admin;

import com.blueocn.api.controller.ui.AbstractUIController;
import org.springframework.stereotype.Controller;
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
public class AdminController extends AbstractUIController {

    @RequestMapping("admin")
    public String index() {
        return "redirect:/admin/index";
    }

    @RequestMapping("admin/index")
    public String home() {
        return "admin/index";
    }
}
