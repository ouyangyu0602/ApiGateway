package com.blueocn.api.kong.connector;

import com.blueocn.api.kong.model.EnabledPlugins;
import com.blueocn.api.kong.model.Plugin;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

/**
 * Title: PluginConnector
 * Description: Kong 插件操作连接类
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-02-25 16:37
 */
public interface PluginConnector {

    /**
     * 给某个 API 添加插件
     *
     * @param apiId  API的ID, 此处不使用API的名称, 不便于标识
     * @param plugin 插件信息
     * @return 创建的插件信息
     */
    @POST("/apis/{apiId}/plugins")
    Call<ResponseBody> add(@Path("apiId") String apiId, @Body Plugin plugin);

    /**
     * 查询某个插件配置信息
     *
     * @param pluginId 插件ID
     * @return 插件信息
     */
    @GET("/plugins/{pluginId}")
    Call<ResponseBody> queryOne(@Path("pluginId") String pluginId);

    /**
     * 全部的Plugins列表, 按照查询条件列举
     *
     * @param queryMap 查询参数
     * @return 插件列表
     */
    @GET("/plugins")
    Call<ResponseBody> query(@QueryMap Map<String, Object> queryMap);

    /**
     * 查询某个API对应的插件信息
     *
     * @param apiId API ID
     * @return 插件列表
     */
    @GET("/apis/{apiId}/plugins")
    Call<ResponseBody> querySpecificApi(@Path("apiId") String apiId, @QueryMap Map<String, Object> queryMap);

    /**
     * 更新某个具体的插件配置
     *
     * @param apiId    API ID
     * @param pluginId 插件 ID, 非插件标识( oauth2 那种)
     * @param plugin   待修改更新的插件信息
     * @return 新的插件对象
     */
    @PATCH("/apis/{apiId}/plugins/{pluginId}")
    Call<ResponseBody> update(@Path("apiId") String apiId, @Path("pluginId") String pluginId,
        @Body Plugin plugin);

    /**
     * 新增或者更新插件配置
     *
     * @param apiId  API ID
     * @param plugin 插件实体, ID不为空时执行更新操作
     * @deprecated 创建请使用 {@link #add(String, Plugin)} 修改请使用 {@link #update(String, String, Plugin)}
     * @return 新的插件对象
     */
    @Deprecated
    @PUT("/apis/{apiId}/plugins")
    Call<ResponseBody> updateOrCreate(@Path("apiId") String apiId, @Body Plugin plugin); // NOSONAR

    /**
     * 删除某个具体的插件配置
     *
     * @param apiId    API ID
     * @param pluginId 插件ID
     */
    @DELETE("/apis/{apiId}/plugins/{pluginId}")
    Call<ResponseBody> delete(@Path("apiId") String apiId, @Path("pluginId") String pluginId);

    /**
     * 查询所有Kong上启用的插件
     *
     * @return 启用插件列表
     */
    @GET("/plugins/enabled")
    Call<EnabledPlugins> queryEnabled();

    /**
     * 获取某个具体的API的配置结构
     * TODO NOSONAR 基于插件结构动态生成插件配置页面
     *
     * @param pluginName 插件名称
     * @return 插件结构
     */
    @GET("/plugins/schema/{pluginName}")
    Call<String> pluginSchema(@Path("pluginName") String pluginName);
}
