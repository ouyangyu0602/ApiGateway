package com.blueocn.api.controller.ui.admin;

import com.blueocn.api.controller.ui.AbstractUIController;
import com.blueocn.api.enums.MessageTypeEnum;
import com.blueocn.api.kong.model.Consumer;
import com.blueocn.api.kong.model.consumers.OAuth2;
import com.blueocn.api.service.ConsumerService;
import com.blueocn.api.service.OAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

/**
 * Title: ApplicationController
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-03-08 15:49
 */
@Controller
@RequestMapping("admin/application")
public class ApplicationController extends AbstractUIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationController.class);

    @Autowired
    private OAuthService oAuthService;

    @Autowired
    private ConsumerService consumerService;

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addApplication(Model model) {
        setMessage(model, MessageTypeEnum.INFO, "If you leave client id and client secret empty, Kong will auto-generate them.");
        addConsumers(model);
        return "admin/application/edit";
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String listApplication(Model model) {
        addConsumers(model);
        return "admin/application/list";
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String editApplication(@PathVariable("id") String id, Model model) {
        addConsumers(model);
        OAuth2 oAuth2 = oAuthService.queryOne(id);
        if (oAuth2 != null) {
            model.addAttribute("application", oAuth2);
        } else {
            setMessage(model, MessageTypeEnum.ERROR, "未找到此 ID 对应的应用信息, 请于应用列表页查看是否该应用已被删除, 或者直接刷新页面.");
            LOGGER.info("查询应用信息不存在, 应用 ID {}", id);
        }
        return "admin/application/edit";
    }

    private void addConsumers(Model model) {
        List<Consumer> consumers = consumerService.queryAll(null);
        if (isNotEmpty(consumers)) {
            model.addAttribute("consumers", consumers);
        }
    }
}
