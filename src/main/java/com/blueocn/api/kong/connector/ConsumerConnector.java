package com.blueocn.api.kong.connector;

import com.blueocn.api.kong.model.Consumer;
import com.blueocn.api.kong.model.Consumers;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

/**
 * Title: CustomerConnector
 * Description: Customer HTTP请求定义接口
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-02-22 11:19
 */
public interface ConsumerConnector {

    /**
     * 新增用户
     *
     * @param consumer 用户信息
     * @return 实际 Kong 上的用户信息
     */
    @POST("/consumers")
    Call<Consumer> add(@Body Consumer consumer);

    /**
     * 用户管理
     *
     * @param queryMap 查询参数
     * @return 用户列表
     */
    @GET("/consumers")
    Call<Consumers> query(@QueryMap Map<String, Object> queryMap);

    /**
     * 查询用户信息
     *
     * @param consumerId 用户ID, 此处还可以使用用户名作为参数查询, 但是统一约定, 只用用户ID
     */
    @GET("/consumers/{consumerId}")
    Call<Consumer> queryOne(@Path("consumerId") String consumerId);

    /**
     * 用户信息修改 (只能修改, 无法创建)
     *
     * @param consumerId 用户ID
     * @param consumer   想要修改的用户信息
     */
    @PATCH("/consumers/{consumerId}")
    Call<Consumer> update(@Path("consumerId") String consumerId, @Body Consumer consumer);

    /**
     * 修改或者创建用户, 当参数实体的 id 属性为空时创建, 否则更新
     *
     * @deprecated 请使用 {@link #update(String, Consumer)} 和 {@link #add(Consumer)} 两个方法
     * @param consumer 用户信息
     */
    @PUT("/consumers")
    @Deprecated
    Call<Consumer> updateOrCreate(@Body Consumer consumer); // NOSONAR

    /**
     * 删除某个用户信息
     *
     * @param consumerId 用户 ID
     */
    @DELETE("/consumers/{consumerId}")
    Call<String> delete(@Path("consumerId") String consumerId);
}
