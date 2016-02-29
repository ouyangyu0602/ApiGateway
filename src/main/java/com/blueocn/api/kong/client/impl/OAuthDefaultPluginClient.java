package com.blueocn.api.kong.client.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.blueocn.api.kong.model.Plugin;
import com.blueocn.api.kong.model.Plugins;
import com.blueocn.api.kong.model.configs.OAuth2;
import org.springframework.stereotype.Component;

/**
 * Title: PluginClientImpl
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-02-29 11:13
 */
@Component("oAuth2")
public class OAuthDefaultPluginClient extends DefaultPluginClient<OAuth2> {

    @Override
    protected Plugins<OAuth2> jsonToList(String json) {
        return JSON.parseObject(json, new TypeReference<Plugins<OAuth2>>() {
        });
    }

    @Override
    protected Plugin<OAuth2> jsonToObject(String json) {
        return JSON.parseObject(json, new TypeReference<Plugin<OAuth2>>() {
        });
    }
}
