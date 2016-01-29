package com.blueocn.api.kong.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.blueocn.api.kong.CustomerClient;
import com.blueocn.api.kong.data.CustomerData;
import com.blueocn.api.kong.data.CustomerKeyData;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Title: CustomerClientImpl
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-18 11:10
 */
@Service
public class CustomerClientImpl implements CustomerClient {

    @Value("${kongAddress}")
    private String kongAddress;

    @Value("${kongListenPort}")
    private Integer KongListenPort;

    @Override
    public boolean createCustomer(CustomerData customer) {
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost(
                kongAddress + ":" + KongListenPort + "/consumers/");
            httpPost.addHeader("content-type", "application/json");
            httpPost.addHeader("Accept", "application/json");
            StringEntity entity = new StringEntity(JSON.toJSONString(customer));
            httpPost.setEntity(entity);
            HttpResponse httpResponse = httpClient.execute(httpPost);
            String response = EntityUtils.toString(httpResponse.getEntity());
            CustomerData result = JSON.parseObject(response, new TypeReference<CustomerData>() {
            });
            if (StringUtils.isNotBlank(result.getId())) {
                return true;
            }
        } catch (IOException e) {
            //
        }
        return false;
    }

    @Override
    public List<CustomerKeyData> getKeyList(String customerId) {
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet httpGet = new HttpGet(
                kongAddress + ":" + KongListenPort + "/consumers/" + customerId
                    + "/key-auth/");
            HttpResponse httpResponse = httpClient.execute(httpGet);
            String response = EntityUtils.toString(httpResponse.getEntity());
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                JSONObject object = JSON.parseObject(response);
                return JSONArray.parseArray(object.getString("data"), CustomerKeyData.class);
            }
        } catch (IOException e) {
            //
        }
        return null;
    }

    @Override
    public boolean addKey(String customerId, String key) {
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost(
                kongAddress + ":" + KongListenPort + "/consumers/" + customerId + "/key-auth/");
            Map<String, String> map = Maps.newHashMap();
            map.put("key", key);
            StringEntity entity = new StringEntity(JSON.toJSONString(map));
            httpPost.setEntity(entity);
            HttpResponse httpResponse = httpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == 201) {
                return true;
            }
        } catch (IOException e) {
            //
        }
        return false;
    }

    @Override
    public boolean deleteKey(String customerId, String keyId) {
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpDelete httpDelete = new HttpDelete(
                kongAddress + ":" + KongListenPort + "/consumers/" + customerId
                    + "/key-auth/" + keyId);
            HttpResponse httpResponse = httpClient.execute(httpDelete);
            if (httpResponse.getStatusLine().getStatusCode() == 204) {
                return true;
            }
        } catch (IOException e) {
            //
        }
        return false;
    }
}
