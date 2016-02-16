package com.blueocn.api.support.utils;

import com.alibaba.fastjson.JSON;
import com.blueocn.api.kong.model.Api;
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
}
