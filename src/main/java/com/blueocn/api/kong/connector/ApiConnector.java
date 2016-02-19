package com.blueocn.api.kong.connector;

import com.blueocn.api.kong.model.Api;
import com.blueocn.api.kong.model.Apis;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

/**
 * Title: ApiService
 * Description: API HTTP请求定义接口
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-02-04 15:44
 */
public interface ApiConnector {

    /**
     * 新增 API
     *
     * @param newApi 新API对象
     * @return 创建的API信息
     */
    @POST("/apis")
    Call<Api> add(@Body Api newApi);

    /**
     * API 管理
     *
     * @param queryInfo 查询
     * @return API 查询列表
     */
    @GET("/apis")
    Call<Apis> query(@QueryMap Map<String, Object> queryInfo);

    /**
     * 查询 API
     *
     * @param apiId API的ID, Kong 存储管理, 实际为 Cassandra 的UUID
     * @return 指定ID的API信息
     */
    @GET("/apis/{apiId}")
    Call<Api> queryOne(@Path("apiId") String apiId);

    /**
     * API 信息修改 (只有修改, 无法创建)
     *
     * @param apiId  API ID
     * @param api API 信息
     */
    @PATCH("/apis/{apiId}")
    Call<Api> update(@Path("apiId") String apiId, @Body Api api);

    /**
     * 修改或者创建 API, 当消息对象的ID不存在时则为创建
     *
     * @deprecated 请使用 {@link #update(String, Api)} 和 {@link #add(Api)} 两个方法, 此方法仅做保留定义, 无测试.
     * @param api API 信息
     */
    @PUT("/apis")
    @Deprecated
    Call<Api> updateOrCreate(@Body Api api); // NOSONAR

    /**
     * 删除某个API, 返回值为空 (注意)
     *
     * @param apiId API ID
     */
    @DELETE("/apis/{apiId}")
    Call<String> delete(@Path("apiId") String apiId);
}
