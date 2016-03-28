package com.blueocn.api.kong.client.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.blueocn.api.kong.client.ApiClient;
import com.blueocn.api.kong.connector.ApiConnector;
import com.blueocn.api.kong.connector.Connector;
import com.blueocn.api.kong.model.Api;
import com.blueocn.api.support.utils.Asserts;
import com.google.common.base.Preconditions;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

/**
 * Title: ApiClientImpl
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-02-03 18:41
 */
@Component
public class ApiClientImpl implements ApiClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiClientImpl.class);

    @Autowired
    private Connector connector;

    private ApiConnector apiConnector;

    @PostConstruct
    private void init() { // NOSONAR
        apiConnector = connector.admin(ApiConnector.class);
    }

    @Override
    public Api add(Api api) throws IOException {
        Call<Api> call = apiConnector.add(api);
        Response<Api> response = call.execute();
        if (response.isSuccessful()) {
            return response.body();
        }
        Api result = new Api();
        result.setErrorMessage(response.errorBody().string());
        LOGGER.debug("API 保存失败 - {}", result.getErrorMessage());
        return result;
    }

    @Override
    public List<Api> query(Api api) throws IOException {
        Call<ResponseBody> call = apiConnector.query(api == null ? null : api.toMap());
        Response<ResponseBody> response = call.execute();
        if (response.isSuccessful()) {
            JSONObject object = JSON.parseObject(response.body().string());
            JSONArray array = object.getJSONArray("data");
            return JSON.parseArray(array.toJSONString(), Api.class);
        }
        return null;
    }

    @Override
    public Integer totalSize(Api api) throws IOException {
        Call<ResponseBody> call = apiConnector.query(api == null ? null : api.toMap());
        Response<ResponseBody> response = call.execute();
        if (response.isSuccessful()) {
            JSONObject object = JSON.parseObject(response.body().string());
            return object.getInteger("total");
        }
        return null;
    }

    @Override
    public Api queryOne(String apiId) throws IOException {
        Call<Api> call = apiConnector.queryOne(apiId);
        Response<Api> response = call.execute();
        if (response.isSuccessful()) {
            return response.body();
        }
        Api result = new Api();
        result.setErrorMessage(response.errorBody().string());
        return result;
    }

    @Override
    public Api update(Api api) throws IOException {
        Preconditions.checkNotNull(api, "API 信息不能为空");
        Asserts.checkNotBlank(api.getId(), "API ID 不能为空");
        Call<Api> call = apiConnector.update(api.getId(), api);
        Response<Api> response = call.execute();
        if (response.isSuccessful()) {
            return response.body();
        }
        Api result = new Api();
        result.setErrorMessage(response.errorBody().string());
        return result;
    }

    @Override
    public void delete(String apiId) throws IOException {
        Asserts.checkNotBlank(apiId, "待删除的 API ID 不能为空");
        Call<String> call = apiConnector.delete(apiId);
        call.execute();
    }
}
