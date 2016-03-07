package com.blueocn.api.service.impl;

import com.blueocn.api.kong.client.PluginClient;
import com.blueocn.api.kong.model.Plugin;
import com.blueocn.api.service.PluginService;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Title: PluginServiceImpl
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-03-03 11:23
 */
@Service
public class PluginServiceImpl implements PluginService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PluginServiceImpl.class);

    @Autowired
    private PluginClient pluginClient;

    @Override
    public List<String> queryEnabledPlugins() {
        try {
            return pluginClient.enabledPlugins();
        } catch (IOException e) {
            LOGGER.info("", e);
            return Lists.newArrayList();
        }
    }

    @Override
    public Plugin queryOAuth2Plugin(String apiId) {
        try {
            Preconditions.checkNotNull(apiId, "API ID 不能为空");
            return pluginClient.querySpecificApiAndPlugin(apiId, "oauth2");
        } catch (IOException e) {
            LOGGER.info("", e);
            return null;
        }
    }

    @Override
    public Plugin saveOAuth2Plugin(String apiId, Plugin plugin) {
        try {
            if (StringUtils.isBlank(plugin.getId())) {
                return pluginClient.add(apiId, plugin);
            } else {
                return pluginClient.update(plugin.getId(), plugin);
            }
        } catch (IOException e) {
            LOGGER.info("", e);
            return null;
        }
    }
}
