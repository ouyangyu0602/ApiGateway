package com.blueocn.api.kong.client;

import com.blueocn.api.kong.model.ApiReq;
import com.blueocn.api.kong.model.ApiResp;

import java.io.IOException;

/**
 * Title: ApiClient
 * Description: 调用 API 请求接口
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-02-03 18:35
 */
public interface ApiClient {

    ApiReq add(ApiReq obj) throws IOException;

    ApiResp query(ApiReq obj);
}
