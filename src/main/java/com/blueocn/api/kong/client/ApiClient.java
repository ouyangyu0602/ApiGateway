package com.blueocn.api.kong.client;

import com.blueocn.api.kong.model.Api;
import com.blueocn.api.kong.model.Apis;

import java.io.IOException;

/**
 * Title: ApiClient
 * Description: 调用 Kong API 请求接口
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-02-03 18:35
 */
public interface ApiClient {

    /**
     * 增一个
     *
     * @throws IOException
     */
    Api add(Api api) throws IOException;

    /**
     * 查一堆
     *
     * @throws IOException
     */
    Apis query(Api api) throws IOException;

    /**
     * 查一个
     *
     * @param apiId 其实 API Name 和 API ID 均可以使用, 为了防止混淆, 这里约定只用 API ID.
     * @throws IOException
     */
    Api queryOne(String apiId) throws IOException;

    /**
     * 更新一个
     *
     * @throws IOException
     */
    Api update(Api api) throws IOException;

    /**
     * 删一个
     *
     * @param apiId 其实 API Name 和 API ID 均可以使用, 为了防止混淆, 这里约定只用 API ID.
     * @throws IOException
     */
    void delete(String apiId) throws IOException;
}
