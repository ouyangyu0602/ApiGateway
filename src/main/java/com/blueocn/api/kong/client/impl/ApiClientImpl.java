package com.blueocn.api.kong.client.impl;

import com.blueocn.api.kong.client.ApiClient;
import com.blueocn.api.kong.model.ApiReq;
import com.blueocn.api.kong.model.ApiResp;
import com.blueocn.api.kong.service.ApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * Title: ApiClientImpl
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-02-03 18:41
 */
@Component
public class ApiClientImpl extends BasicClient implements ApiClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiClientImpl.class);

    private ApiService apiService;

    @PostConstruct
    private void init() { // NOSONAR
        apiService = retrofit.create(ApiService.class);
    }

    @Override
    public ApiReq add(ApiReq obj) throws IOException {
        Call<ApiReq> call = apiService.add(obj);
        Response<ApiReq> response = call.execute();
        LOGGER.info("Request Body {}", response.raw().request().toString());
        LOGGER.info("Response Body {}", response.raw().message());
        return response.body();
    }

    @Override
    public ApiResp query(ApiReq obj) {
        return null;
    }
}
