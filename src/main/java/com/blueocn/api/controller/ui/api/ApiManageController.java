package com.blueocn.api.controller.ui.api;

import com.blueocn.api.controller.ui.AbstractUIController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Title: ApiManageController
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-01-29 12:08
 */
@Controller
@RequestMapping("api")
public class ApiManageController extends AbstractUIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiManageController.class);

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String listApi(@RequestParam(value = "offset", required = false) String offset,
        @RequestParam(value = "size", required = false) Integer size, Model model) {
        return null;
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addApi(Model model) {
        return "api/edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String editApi(@RequestParam(value = "id") String id, Model model) {
        model.addAttribute("api", id);
        return "api/edit";
    }
}
