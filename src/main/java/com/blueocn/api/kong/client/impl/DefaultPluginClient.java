package com.blueocn.api.kong.client.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.blueocn.api.kong.model.Plugin;
import com.blueocn.api.kong.model.Plugins;
import org.springframework.stereotype.Component;

/**
 * Title: DefaultPluginClient
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-03-03 12:29
 */
@Component("defaultPluginClient")
public class DefaultPluginClient extends AbstractPluginClient<String> {

    @Override
    protected Plugins<String> jsonToList(String json) {
        return JSON.parseObject(json, new TypeReference<Plugins<String>>() {
        });
    }

    @Override
    protected Plugin<String> jsonToObject(String json) {
        return JSON.parseObject(json, new TypeReference<Plugin<String>>() {
        });
    }

    @Override
    protected String getPluginName() {
        return null;
    }
}
