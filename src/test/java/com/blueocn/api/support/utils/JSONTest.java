package com.blueocn.api.support.utils;

import com.alibaba.fastjson.JSON;
import com.blueocn.api.kong.model.Api;
import com.blueocn.api.kong.model.Plugin;
import com.blueocn.api.kong.model.configs.OAuth2Config;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class JSONTest {

    @Test
    public void testSerialize() throws Exception {
        Api api = new Api();
        api.setName("name");
        api.setRequestHost("host");
        api.setRequestPath("path");
        api.setUpstreamUrl("url");
        String json = JSON.toJSONString(api);

        Assert.assertTrue(json.contains("\"name\":\"name\""));
        Assert.assertTrue(json.contains("\"request_host\":\"host\""));
        Assert.assertTrue(json.contains("\"request_path\":\"path\""));
        Assert.assertTrue(json.contains("\"upstream_url\":\"url\""));

        String testStr =
            "[{\"name\":\"name\",\"preserve_host\":false,\"request_host\":\"host\",\"request_path\":\"path\",\"strip_request_path\":false,\"upstream_url\":\"url\"},"
                + "{\"name\":\"name\",\"preserve_host\":false,\"request_host\":\"host\",\"request_path\":\"path\",\"strip_request_path\":false,\"upstream_url\":\"url\"},"
                + "{\"name\":\"name\",\"preserve_host\":false,\"request_host\":\"host\",\"request_path\":\"path\",\"strip_request_path\":false,\"upstream_url\":\"url\"}]";

        List<Api> list = JSON.parseArray(testStr, Api.class);
        Assert.assertEquals(list.size(), 3);
    }

    @Test
    public void testPlugins() throws Exception {
        Plugin plugin = new Plugin();
        OAuth2Config oAuth2Config = new OAuth2Config();
        oAuth2Config.setScopes(new String[] {"a", "b", "c", "d"});
        plugin.setConfig(oAuth2Config);
        plugin.setName("OAuth2");
        String json = JSON.toJSONString(plugin);

        Assert.assertTrue(json.contains("\"name\":\"OAuth2\""));
    }
}
