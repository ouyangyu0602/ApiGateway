package com.blueocn.api.kong.client.impl;

import com.alibaba.fastjson.JSON;
import com.blueocn.api.BaseTest;
import com.blueocn.api.kong.client.ApiClient;
import com.blueocn.api.kong.model.Api;
import com.blueocn.api.kong.model.Apis;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static org.apache.commons.collections.CollectionUtils.isNotEmpty;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class ApiClientImplTest extends BaseTest {

    @Autowired
    private ApiClient client;

    @Test
    public void testAdd() throws Exception {
        Api req = randomApi();
        Api result = client.add(req);

        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getId());

        logger.info(JSON.toJSONString(result));
    }

    @Test
    public void testQuery() throws Exception {
        Apis apis = client.query(null);
        logger.info("Query Str {}", JSON.toJSONString(apis));
    }

    @Test
    public void testQueryOne() throws Exception {
        Apis apis = client.query(null);
        if (apis != null && isNotEmpty(apis.getData())) {
            Api api = apis.getData().get(0);
            Api api1 = client.queryOne(api.getId());
            Assert.assertEquals(JSON.toJSONString(api), JSON.toJSONString(api1));
        }
    }

    @Test
    public void testUpdate() throws Exception {
        Apis apis = client.query(null);
        if (apis != null && isNotEmpty(apis.getData())) {
            Api api = apis.getData().get(0);
            api.setName(UUID.randomUUID().toString().replaceAll("-", ""));
            client.update(api);

            Api api1 = client.queryOne(api.getId());
            Assert.assertEquals(JSON.toJSONString(api), JSON.toJSONString(api1));
        }
    }

    @Test
    public void testDelete() throws Exception {
        Api api = randomApi();
        String newApiId = client.add(api).getId();
        Api api1 = client.queryOne(newApiId);

        Assert.assertNotNull(api1);
        Assert.assertEquals(newApiId, api1.getId());

        client.delete(newApiId);

        Api api2 = client.queryOne(newApiId);
        Assert.assertNull(api2 == null ? api2 : api2.getId());
    }

    private Api randomApi() {
        return Api.builder().name(UUID.randomUUID().toString().replaceAll("-", ""))
            .requestHost(randomAlphabetic(8) + ".com")
            .upstreamUrl("http://" + randomAlphabetic(8) + ".com")
            .build();
    }
}
