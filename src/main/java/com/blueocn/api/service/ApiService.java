package com.blueocn.api.service;

import com.blueocn.api.kong.model.Api;
import com.blueocn.api.kong.model.Apis;
import com.blueocn.api.response.RestfulResponse;

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

    /**
     * 保存 API
     *
     * @param api 待保存的 API 实体
     * @return 存储结果
     */
    RestfulResponse<String> save(Api api);

    /**
     * 查询全部符合查询条件的 API
     *
     * @param api 查询的API参数
     * @return 查询结果
     */
    Apis queryAll(Api api);

    /**
     * 删除 API
     * @param apiId API ID
     */
    void delete(String apiId);
}
