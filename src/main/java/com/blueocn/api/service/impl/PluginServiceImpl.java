package com.blueocn.api.service.impl;

import com.blueocn.api.kong.client.PluginClient;
import com.blueocn.api.kong.model.Plugin;
import com.blueocn.api.response.RestfulResponse;
import com.blueocn.api.service.PluginService;
import com.blueocn.api.support.utils.Asserts;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;

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
    public RestfulResponse saveOAuth2Plugin(String apiId, Plugin plugin) {
        Asserts.checkNotBlank(apiId, "待保存插件对应的 API ID 不能为空.");
        Plugin newPlugin;
        try {
            if (isBlank(plugin.getId())) {
                newPlugin = pluginClient.add(apiId, plugin);
            } else {
                newPlugin = pluginClient.update(plugin.getId(), plugin);
            }
        } catch (IOException e) {
            LOGGER.info("", e);
            return new RestfulResponse(e.getMessage());
        }
        if (newPlugin != null) {
            return new RestfulResponse(newPlugin.getErrorMessage());
        }
        return new RestfulResponse("插件信息保存失败");
    }

    @Override
    public List<Plugin> queryAll(Plugin plugin) {
        Plugin queryPlugin = plugin == null ? new Plugin() : plugin;
        queryPlugin.setSize(getPluginAmount(queryPlugin));
        try {
            return pluginClient.query(queryPlugin);
        } catch (IOException e) {
            LOGGER.info("", e);
        }
        return Lists.newArrayList();
    }

    private Integer getPluginAmount(Plugin plugin) {
        try {
            Plugin queryPlugin = plugin == null ? new Plugin() : plugin;
            queryPlugin.setSize(1);
            return pluginClient.totalSize(queryPlugin);
        } catch (IOException e) {
            LOGGER.info("", e);
        }
        return null;
    }
}
