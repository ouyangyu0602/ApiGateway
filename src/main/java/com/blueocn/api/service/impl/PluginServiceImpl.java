package com.blueocn.api.service.impl;

import com.blueocn.api.kong.client.PluginClient;
import com.blueocn.api.kong.model.Plugin;
import com.blueocn.api.kong.model.configs.OAuth2Config;
import com.blueocn.api.service.PluginService;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Qualifier("defaultPluginClient")
    @Autowired
    private PluginClient<String> pluginClient;

    @Qualifier("oAuth2PluginClient")
    @Autowired
    private PluginClient<OAuth2Config> oAuth2PluginClient;

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
    public Plugin<OAuth2Config> queryOAuth2Plugin(String apiId) {
        try {
            Preconditions.checkNotNull(apiId, "API ID 不能为空");
            return oAuth2PluginClient.querySpecificApiAndPlugin(apiId);
        } catch (IOException e) {
            LOGGER.info("", e);
            return null;
        }
    }

    @Override
    public Plugin<OAuth2Config> saveOAuth2Plugin(String apiId, Plugin<OAuth2Config> plugin) {
        try {
            if (StringUtils.isBlank(plugin.getId())) {
                return oAuth2PluginClient.add(apiId, plugin);
            } else {
                return oAuth2PluginClient.update(plugin.getId(), plugin);
            }
        } catch (IOException e) {
            LOGGER.info("", e);
            return null;
        }
    }
}
