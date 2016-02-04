package com.blueocn.api.kong.client;

import com.blueocn.api.kong.request.ApiReq;
import com.blueocn.api.kong.response.ApiResp;

/**
 * Title: ApiClient
 * Description: 调用 API 请求接口
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-02-03 18:35
 */
public interface ApiClient extends BaseClient<ApiReq, ApiResp> {
}
