package com.blueocn.api.kong.client.impl;

import com.alibaba.fastjson.JSON;
import com.blueocn.api.BaseTest;
import com.blueocn.api.kong.client.ApiClient;
import com.blueocn.api.kong.client.PluginClient;
import com.blueocn.api.kong.model.Api;
import com.blueocn.api.kong.model.Plugin;
import com.blueocn.api.kong.model.configs.OAuth2;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.UUID;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class OAuthDefaultPluginClientTest extends BaseTest {

    @Qualifier("oAuth2")
    @Autowired
    private PluginClient<OAuth2> pluginClient;

    @Autowired
    private ApiClient apiClient;

    @Test
    public void testAdd() throws Exception {
        Api req = randomApi();
        Api result = apiClient.add(req);

        OAuth2 oAuth2 = new OAuth2();
        oAuth2.setScopes(new String[] {"email", "phone", "name"});
        oAuth2.setMandatoryScope(true);
        Plugin<OAuth2> plugin = new Plugin<>();
        plugin.setConfig(oAuth2);
        plugin.setName("oauth2");

        Plugin<OAuth2> plugin2 = pluginClient.add(result.getId(), plugin);

        logger.info(JSON.toJSONString(plugin2));

        OAuth2 oAuth = plugin2.getConfig();
        Assert.assertNotNull(plugin2);
        Assert.assertNotNull(oAuth);

        Plugin<OAuth2> oAuth2Plugin = pluginClient.queryOne(plugin2.getId());
        Assert.assertNotNull(oAuth2Plugin);
    }

    private Api randomApi() {
        return Api.builder().name(UUID.randomUUID().toString().replaceAll("-", ""))
            .requestHost(randomAlphabetic(8) + ".com")
            .upstreamUrl("http://" + randomAlphabetic(8) + ".com").build();
    }
}
