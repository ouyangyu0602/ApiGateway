package com.blueocn.api.controller.ui.admin;

import com.blueocn.api.controller.ui.AbstractUIController;
import com.blueocn.api.kong.KongConfig;
import com.blueocn.api.kong.model.Api;
import com.blueocn.api.kong.model.Plugin;
import com.blueocn.api.service.ApiService;
import com.blueocn.api.service.PluginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

/**
 * Title: PluginController
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-03-02 18:48
 */
@Controller
@RequestMapping("admin")
public class PluginController extends AbstractUIController {

    @Autowired
    private ApiService apiService;

    @Autowired
    private PluginService pluginService;

    @Autowired
    private KongConfig kongConfig;

    @RequestMapping(value = "plugin/select", method = RequestMethod.GET)
    public String addPlugin(Model model) {
        List<Api> apis = apiService.queryAll(null);
        if (isNotEmpty(apis)) {
            model.addAttribute("apiList", apis);
        }
        List<String> pluginList = pluginService.queryEnabledPlugins();
        if (isNotEmpty(pluginList)) {
            model.addAttribute("pluginList", pluginList);
        }
        return "admin/plugin/select";
    }

    @RequestMapping(value = "api/{apiId}/plugin/oauth2", method = RequestMethod.GET)
    public String oAuth2Plugin(@PathVariable("apiId") String apiId, Model model) {
        Plugin existPlugin = pluginService.queryOAuth2Plugin(apiId);
        model.addAttribute("apiId", apiId);
        if (existPlugin != null) {
            model.addAttribute("oAuth2", existPlugin);
        }
        model.addAttribute("defaultProvisionKey", kongConfig.getProvisionKey());
        return "admin/plugin/oauth2";
    }

    @RequestMapping(value = "plugin/list", method = RequestMethod.GET)
    public String listPlugin() {
        // TODO 插件列表
        return "admin/plugin/list";
    }
}
