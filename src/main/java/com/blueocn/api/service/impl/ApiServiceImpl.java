package com.blueocn.api.service.impl;

import com.blueocn.api.kong.client.ApiClient;
import com.blueocn.api.kong.model.Api;
import com.blueocn.api.service.ApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Title: ApiServiceImpl
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-02-19 10:49
 */
@Service
public class ApiServiceImpl implements ApiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiServiceImpl.class);

    @Autowired
    private ApiClient client;

    @Override
    public Api query(String apiId) {
        try {
            Api api = client.queryOne(apiId);
            LOGGER.debug("API的信息: {}", api);
            return api;
        } catch (IOException e) {
            LOGGER.warn("", e);
        }
        return null;
    }
}
