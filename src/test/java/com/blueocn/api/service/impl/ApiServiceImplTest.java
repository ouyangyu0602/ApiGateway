package com.blueocn.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.blueocn.api.BaseTest;
import com.blueocn.api.kong.model.Apis;
import com.blueocn.api.service.ApiService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ApiServiceImplTest extends BaseTest {

    @Autowired
    private ApiService apiService;

    @Test
    public void testQueryAll() throws Exception {
        Apis apis = apiService.queryAll(null);
        logger.info("API 信息 {}", JSON.toJSONString(apis));
    }
}
