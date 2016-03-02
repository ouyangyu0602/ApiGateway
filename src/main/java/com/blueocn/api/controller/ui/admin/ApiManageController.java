package com.blueocn.api.controller.ui.admin;

import com.blueocn.api.controller.ui.AbstractUIController;
import com.blueocn.api.kong.model.Api;
import com.blueocn.api.service.ApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Title: ApiManageController
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-01-29 12:08
 */
@Controller
@RequestMapping("admin/api")
public class ApiManageController extends AbstractUIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiManageController.class);

    @Autowired
    private ApiService apiService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String listApi() {
        return "admin/api/list";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addApi() {
        return "admin/api/edit";
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String editApi(@PathVariable(value = "id") String id, Model model) {
        Api api = apiService.query(id);
        if (api != null) {
            model.addAttribute("api", api);
        } else {
            LOGGER.info("此 API 不存在, API ID {}", id);
        }
        return "admin/api/edit";
    }
}
