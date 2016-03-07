package com.blueocn.api.controller.rest.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.blueocn.api.controller.rest.AbstractResponseController;
import com.blueocn.api.kong.model.Plugin;
import com.blueocn.api.response.RestfulResponse;
import com.blueocn.api.service.PluginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Title: RestPluginController
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-03-02 19:00
 */
@RestController
@RequestMapping("admin")
public class RestPluginController extends AbstractResponseController {

    @Autowired
    private PluginService pluginService;

    @RequestMapping(value = "api/{apiId}/plugin/oauth2", method = RequestMethod.POST)
    public RestfulResponse oAuth2Plugin(@PathVariable("apiId") String apiId, @RequestParam(value = "formData") String queryStr) {
        Plugin plugin = JSON.parseObject(queryStr, new TypeReference<Plugin>() {});
        plugin.setName("oauth2");
        Plugin newPlugin = pluginService.saveOAuth2Plugin(apiId, plugin);
        String errorMessage = newPlugin == null ? "创建失败" : newPlugin.getErrorMessage();
        if (isNotBlank(errorMessage)) {
            return new RestfulResponse(errorMessage);
        }
        return new RestfulResponse();
    }
}
