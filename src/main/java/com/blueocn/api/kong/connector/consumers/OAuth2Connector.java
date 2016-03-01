package com.blueocn.api.kong.connector.consumers;

import com.blueocn.api.kong.model.consumers.OAuth2;
import com.blueocn.api.kong.model.consumers.OAuth2s;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

/**
 * Title: OAuth2Connector
 * Description: 用户 OAuth2 配置信息连接类
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-03-01 16:16
 */
public interface OAuth2Connector {

    /**
     * 新增 OAuth 应用
     *
     * @param oAuth2 应用信息
     * @return 认证后的结果
     */
    @POST("/consumers/{consumerId}/oauth2")
    Call<OAuth2> add(@Path("consumerId") String consumerId, @Body OAuth2 oAuth2);

    /**
     * 查询某个开发者的 OAuth 应用
     *
     * @param consumerId 开发者ID
     * @param queryInfo  查询信息
     * @return 应用列表
     */
    @GET("/consumers/{consumerId}/oauth2")
    Call<OAuth2s> query(@Path("consumerId") String consumerId, @QueryMap Map<String, Object> queryInfo);

    /**
     * 查询指定的应用信息
     *
     * @param consumerId 开发者ID
     * @param oauth2Id   应用ID
     * @return 应用信息
     */
    @GET("/consumers/{consumerId}/oauth2/{oauth2Id}")
    Call<OAuth2> queryOne(@Path("consumerId") String consumerId, @Path("oauth2Id") String oauth2Id);

    /**
     * 更新指定应用的信息, 一般是刷新两码
     *
     * @param consumerId 开发者ID
     * @param oauth2Id   应用ID
     * @param oAuth2     应用更新信息
     * @return 新的应用信息
     */
    @PATCH("/consumers/{consumerId}/oauth2/{oauth2Id}")
    Call<OAuth2> update(@Path("consumerId") String consumerId, @Path("oauth2Id") String oauth2Id,
        @Body OAuth2 oAuth2);

    /**
     * 删除应用
     *
     * @param consumerId 开发者ID
     * @param oauth2Id   应用ID
     */
    @DELETE("/consumers/{consumerId}/oauth2/{oauth2Id}")
    Call<String> delete(@Path("consumerId") String consumerId, @Path("oauth2Id") String oauth2Id);
}
