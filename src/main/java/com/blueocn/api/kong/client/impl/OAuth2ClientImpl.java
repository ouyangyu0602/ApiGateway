package com.blueocn.api.kong.client.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.blueocn.api.kong.client.OAuth2Client;
import com.blueocn.api.kong.connector.Connector;
import com.blueocn.api.kong.connector.FrontConnector;
import com.blueocn.api.kong.connector.consumers.OAuth2Connector;
import com.blueocn.api.kong.model.consumers.OAuth2;
import com.blueocn.api.response.RestfulResponse;
import com.google.common.collect.Lists;
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
import java.util.Map;

/**
 * Title: OAuth2ClientImpl
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-03-01 18:15
 */
@Component
public class OAuth2ClientImpl implements OAuth2Client {

    private static final Logger LOGGER = LoggerFactory.getLogger(OAuth2ClientImpl.class);

    @Autowired
    private Connector connector;

    private OAuth2Connector oAuth2Connector;

    private FrontConnector frontConnector;

    @PostConstruct
    private void init() {
        oAuth2Connector = connector.admin(OAuth2Connector.class);
        frontConnector = connector.front(FrontConnector.class);
    }

    @Override
    public OAuth2 add(String consumerId, OAuth2 oAuth2) throws IOException {
        Call<OAuth2> call = oAuth2Connector.add(consumerId, oAuth2);
        Response<OAuth2> response = call.execute();
        if (response.isSuccess()) {
            return response.body();
        }
        return null;
    }

    @Override
    public List<OAuth2> query(OAuth2 oAuth2) throws IOException {
        Call<ResponseBody> call = oAuth2Connector.rawQuery(oAuth2 == null ? null : oAuth2.toMap());
        Response<ResponseBody> response = call.execute();
        if (response.isSuccess()) {
            JSONObject object = JSON.parseObject(response.body().string());
            JSONArray array = object.getJSONArray("data");
            return JSON.parseArray(array.toJSONString(), OAuth2.class);
        }
        return Lists.newArrayList();
    }

    @Override
    public List<OAuth2> query(String consumerId, OAuth2 oAuth2) throws IOException {
        Call<ResponseBody> call = oAuth2Connector.query(consumerId, oAuth2 == null ? null : oAuth2.toMap());
        Response<ResponseBody> response = call.execute();
        if (response.isSuccess()) {
            JSONObject object = JSON.parseObject(response.body().string());
            JSONArray array = object.getJSONArray("data");
            return JSON.parseArray(array.toJSONString(), OAuth2.class);
        }
        return Lists.newArrayList();
    }

    @Override
    public OAuth2 queryOne(String consumerId, String oauth2Id) throws IOException {
        Call<OAuth2> call = oAuth2Connector.queryOne(consumerId, oauth2Id);
        Response<OAuth2> response = call.execute();
        if (response.isSuccess()) {
            return response.body();
        }
        return null;
    }

    @Override
    public OAuth2 update(String consumerId, String oauth2Id, OAuth2 oAuth2) throws IOException {
        Call<OAuth2> call = oAuth2Connector.update(consumerId, oauth2Id, oAuth2);
        Response<OAuth2> response = call.execute();
        if (response.isSuccess()) {
            return response.body();
        }
        return null;
    }

    @Override
    public void delete(String consumerId, String oauth2Id) throws IOException {
        oAuth2Connector.delete(consumerId, oauth2Id).execute();
    }

    @Override
    public RestfulResponse authorize(Map<String, String> params, String host) {
        Call<ResponseBody> call = frontConnector.authorize(params, host);
        try {
            Response<ResponseBody> response = call.execute();
            if (response.isSuccess()) {
                JSONObject object = JSON.parseObject(response.body().string());
                RestfulResponse restfulResponse = new RestfulResponse();
                restfulResponse.setCode(object.getString("redirect_uri"));
                return restfulResponse;
            }
            return new RestfulResponse(response.errorBody().string());
        } catch (IOException e) {
            LOGGER.info("", e);
            return new RestfulResponse(e.getMessage());
        }
    }

    @Override
    public Integer totalSize(OAuth2 oAuth) throws IOException {
        Call<ResponseBody> call = oAuth2Connector.rawQuery(oAuth == null ? null : oAuth.toMap());
        Response<ResponseBody> response = call.execute();
        if (response.isSuccess()) {
            JSONObject object = JSON.parseObject(response.body().string());
            return object.getInteger("total");
        }
        return null;
    }
}
