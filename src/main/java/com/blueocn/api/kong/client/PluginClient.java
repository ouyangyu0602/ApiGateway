package com.blueocn.api.kong.client;

import com.blueocn.api.kong.model.Plugin;

import java.io.IOException;
import java.util.List;

/**
 * Title: PluginClient
 * Description: 通用插件调用接口类
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-02-29 17:31
 */
public interface PluginClient {

    /**
     * 新增一个插件配置
     *
     * @param apiId  API ID
     * @param plugin 插件信息
     * @return 成功创建的插件对象
     * @throws IOException
     */
    Plugin add(String apiId, Plugin plugin) throws IOException;

    /**
     * 查询某个配置信息
     *
     * @param pluginId 配置信息 ID
     * @throws IOException
     */
    Plugin queryOne(String pluginId) throws IOException;

    /**
     * 查询配置信息列表
     *
     * @param plugin 查询参数
     * @throws IOException
     */
    List<Plugin> query(Plugin plugin) throws IOException;

    /**
     * 查询某个具体API对应的插件配置
     *
     * @param apiId  API ID
     * @param plugin 插件查询参数
     * @return 配置列表
     */
    List<Plugin> querySpecificApi(String apiId, Plugin plugin) throws IOException;

    /**
     * 查询某个具体的 API 对应的某个插件信息, 由最终的接口实现类定义插件是什么
     *
     * @param apiId API ID
     */
    Plugin querySpecificApiAndPlugin(String apiId, String pluginName) throws IOException;

    /**
     * 更新插件信息
     *
     * @param pluginId 插件ID
     * @param plugin   待更新的插件信息
     * @return 更新后的插件
     */
    Plugin update(String pluginId, Plugin plugin) throws IOException;

    /**
     * 删除插件信息
     *
     * @param pluginId 插件ID
     */
    void delete(String pluginId) throws IOException;

    /**
     * 删除插件信息
     *
     * @param apiId    插件对应的API ID
     * @param pluginId 插件ID
     */
    void delete(String apiId, String pluginId) throws IOException;

    /**
     * 查询所有启用的 API
     *
     * @return API的名称集合, @NotNull
     */
    List<String> enabledPlugins() throws IOException;
}
