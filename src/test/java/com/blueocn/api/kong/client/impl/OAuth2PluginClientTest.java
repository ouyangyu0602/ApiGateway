package com.blueocn.api.kong.client.impl;

import com.alibaba.fastjson.JSON;
import com.blueocn.api.BaseTest;
import com.blueocn.api.kong.client.ApiClient;
import com.blueocn.api.kong.client.PluginClient;
import com.blueocn.api.kong.model.Api;
import com.blueocn.api.kong.model.Plugin;
import com.blueocn.api.kong.model.Plugins;
import com.blueocn.api.kong.model.configs.OAuth2Config;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.UUID;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class OAuth2PluginClientTest extends BaseTest {

    @Qualifier("oAuth2")
    @Autowired
    private PluginClient<OAuth2Config> pluginClient;

    @Autowired
    private ApiClient apiClient;

    @Test
    public void testAdd() throws Exception {
        Api req = randomApi();
        Api newApi = apiClient.add(req);

        // 新增插件
        OAuth2Config oAuth1 = new OAuth2Config();
        oAuth1.setScopes(new String[] {"email", "phone", "name"});
        oAuth1.setMandatoryScope(true);
        Plugin<OAuth2Config> plugin1 = new Plugin<>();
        plugin1.setConfig(oAuth1);
        plugin1.setName("oauth2");

        Plugin<OAuth2Config> plugin2 = pluginClient.add(newApi.getId(), plugin1);

        logger.info(JSON.toJSONString(plugin2));

        OAuth2Config oAuth2Config = plugin2.getConfig();
        Assert.assertTrue(plugin2.getName().equals(plugin1.getName()));
        Assert.assertArrayEquals(oAuth2Config.getScopes(), oAuth1.getScopes());

        // 查询插件
        Plugin<OAuth2Config> plugin3 = pluginClient.queryOne(plugin2.getId());
        Assert.assertNotNull(plugin3);
        Assert.assertEquals(plugin2.getApiId(), plugin3.getApiId());
        Assert.assertEquals(plugin3.getApiId(), newApi.getId());

        // 查询具体的API对应的插件
        Plugins<OAuth2Config> plugins1 = pluginClient.querySpecificApi(plugin3.getApiId(), null);
        Assert.assertNotNull(plugins1);
        Assert.assertEquals(plugins1.getTotal(), new Integer(1));

        // 删除API
        pluginClient.delete(plugin3.getApiId(), plugin3.getId());
        Plugin<OAuth2Config> plugin4 = pluginClient.queryOne(plugin3.getId());
        Assert.assertNull(plugin4.getApiId());

        apiClient.delete(plugin3.getApiId());
    }

    private Api randomApi() {
        return Api.builder().name(UUID.randomUUID().toString().replaceAll("-", ""))
            .requestHost(randomAlphabetic(8) + ".com")
            .upstreamUrl("http://" + randomAlphabetic(8) + ".com").build();
    }
}
