package com.blueocn.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.blueocn.api.BaseTest;
import com.blueocn.api.kong.model.Api;
import com.blueocn.api.service.ApiService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ApiServiceImplTest extends BaseTest {

    @Autowired
    private ApiService apiService;

    @Test
    public void testQueryAll() throws Exception {
        List<Api> apis = apiService.queryAll(null);
        logger.info("API 信息 {}", JSON.toJSONString(apis));
    }
}
