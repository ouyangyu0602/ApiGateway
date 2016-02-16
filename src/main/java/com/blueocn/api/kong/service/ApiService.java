package com.blueocn.api.kong.service;

import com.blueocn.api.kong.model.ApiReq;
import com.blueocn.api.kong.model.ApiResp;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * Title: ApiService
 * Description: API HTTP请求定义接口
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-02-04 15:44
 */
public interface ApiService {

    /**
     * 新增 API
     *
     * @param newApi 新API对象
     * @return 创建的API信息
     */
    @POST("/apis")
    Call<ApiReq> add(@Body ApiReq newApi);

    /**
     * 查询 API
     *
     * @param apiId API的ID, Kong 存储管理, 实际为 Cassandra 的UUID
     * @return 指定ID的API信息
     */
    @GET("/apis/{apiId}")
    Call<ApiReq> queryOne(@Path("apiId") String apiId);

    /**
     * API 管理
     *
     * @param queryInfo 查询
     * @return API 查询列表
     */
    @GET("/apis")
    Call<ApiResp> query(@Query("") ApiReq queryInfo);

    /**
     * API 信息修改 (只有修改, 无法创建)
     *
     * @param apiId  API ID
     * @param apiReq API 信息
     */
    @PATCH("/apis/{apiId}")
    Call<ApiReq> update(@Path("apiId") String apiId, ApiReq apiReq);

    /**
     * 修改或者创建 API, 当消息对象的ID不存在时则为创建
     *
     * @param apiReq API 信息
     */
    @PUT("/apis")
    Call<ApiReq> updateOrCreate(ApiReq apiReq);

    /**
     * 删除某个API
     *
     * @param apiId API ID
     */
    @DELETE("/apis/{apiId}")
    Call<String> delete(@Path("apiId") String apiId);
}
