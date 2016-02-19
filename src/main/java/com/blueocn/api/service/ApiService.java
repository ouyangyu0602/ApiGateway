package com.blueocn.api.service;

import com.blueocn.api.kong.model.Api;

/**
 * Title: ApiService
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-02-19 10:46
 */
public interface ApiService {

    /**
     * 通过 API ID 查询 API 信息
     *
     * @param apiId API 在 Kong 上注册时候的 ID
     * @return 当 API 信息不存在时, 会返回 null
     */
    Api query(String apiId);
}
