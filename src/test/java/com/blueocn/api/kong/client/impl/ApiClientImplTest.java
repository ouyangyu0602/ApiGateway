package com.blueocn.api.kong.client.impl;

import com.alibaba.fastjson.JSON;
import com.blueocn.api.BaseTest;
import com.blueocn.api.kong.client.ApiClient;
import com.blueocn.api.kong.model.ApiReq;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class ApiClientImplTest extends BaseTest {

    @Autowired
    private ApiClient client;

    @Test
    public void testAdd() throws Exception {
        ApiReq req = ApiReq.builder()
            .name(UUID.randomUUID().toString().replaceAll("-", ""))
            .requestHost("mockbin.com")
            .upstreamUrl("http://mockbin.com").build();
        ApiReq result = client.add(req);

        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getId());

        logger.error(JSON.toJSONString(result));
    }
}
