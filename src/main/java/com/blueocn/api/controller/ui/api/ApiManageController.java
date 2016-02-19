package com.blueocn.api.controller.ui.api;

import com.blueocn.api.controller.ui.AbstractUIController;
import com.blueocn.api.kong.model.Api;
import com.blueocn.api.service.ApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ApiService apiService;

//    @RequestMapping(value = "list", method = RequestMethod.GET)
//    public String listApi(@RequestParam(value = "offset", required = false) String offset,
//        @RequestParam(value = "size", required = false) Integer size, Model model) {
//        return null;
//    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addApi() {
        return "api/edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String editApi(@RequestParam(value = "id") String id, Model model) {
        Api api = apiService.query(id);
        if (api != null) {
            model.addAttribute("api", api);
        } else {
            LOGGER.info("此 API 不存在, API ID {}", id);
            // TODO 添加异常页面, 处理展示
        }
        return "api/edit";
    }
}
