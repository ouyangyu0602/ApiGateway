package com.blueocn.api.kong.connector;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

import java.util.Map;

/**
 * Title: FrontConnector
 * Description: Kong 前端地址请求集合, 非管理地址请求, 因为比较零散, 现在只定义一个 Connector
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-03-08 11:32
 */
public interface FrontConnector {

    /**
     * 认证应用, 获取回调地址
     *
     * @param params 认证参数
     * @param host   API 请求头
     * @return 认证结果
     */
    @POST("/oauth2/authorize")
    @FormUrlEncoded
    Call<ResponseBody> authorize(@FieldMap Map<String, String> params, @Header("Host") String host);
}
