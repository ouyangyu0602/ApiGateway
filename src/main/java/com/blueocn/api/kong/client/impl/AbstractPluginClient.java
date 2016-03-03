package com.blueocn.api.kong.client.impl;

import com.blueocn.api.kong.client.PluginClient;
import com.blueocn.api.kong.connector.Connector;
import com.blueocn.api.kong.connector.PluginConnector;
import com.blueocn.api.kong.model.EnabledPlugins;
import com.blueocn.api.kong.model.Plugin;
import com.blueocn.api.kong.model.Plugins;
import com.blueocn.api.support.utils.Asserts;
import com.google.common.collect.Lists;
import okhttp3.ResponseBody;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import retrofit2.Call;
import retrofit2.Response;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Title: PluginClient
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-02-29 11:13
 */
public abstract class AbstractPluginClient<T extends Serializable> implements PluginClient<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractPluginClient.class);

    @Autowired
    private Connector connector;

    private PluginConnector pluginConnector;

    @PostConstruct
    public void init() { // NOSONAR
        pluginConnector = connector.create(PluginConnector.class);
    }

    /**
     * 将响应报文转换为List
     *
     * @param json 响应报文, JSON格式
     * @return List 集合
     */
    protected abstract Plugins<T> jsonToList(String json);

    /**
     * 将响应报文转换为实际的包含泛型的插件配置对象
     *
     * @param json 响应报文, JSON格式
     * @return 实际的配置对象
     */
    protected abstract Plugin<T> jsonToObject(String json); // NOSONAR

    /**
     * 获取插件名称, 用于查询
     *
     * @return 插件名称
     */
    protected abstract String getPluginName();

    /**
     * 从插件 ID 获取它对应的 API ID
     *
     * @param pluginId 插件 ID
     * @return API ID
     * @throws IOException
     */
    private String getApiIdByPluginId(String pluginId) throws IOException {
        Plugin<T> existPlugin = queryOne(pluginId);
        return existPlugin.getApiId();
    }

    /**
     * 查询某个名称的插件是否在 Kong 上启用
     *
     * @param pluginName 插件名称
     */
    private Boolean isPluginEnabled(String pluginName) {
        try {
            List<String> pluginList = enabledPlugins();
            return pluginList.contains(pluginName);
        } catch (IOException e) {
            LOGGER.info("", e);
        }
        return false;
    }

    @Override
    public Plugin<T> add(String apiId, Plugin plugin) throws IOException {
        if (!isPluginEnabled(plugin.getName())) {
            throw new IllegalArgumentException(String.format("插件 %s 未启用, 请联系管理员或稍后重试.", plugin.getName()));
        }
        Call<ResponseBody> call = pluginConnector.add(apiId, plugin);
        Response<ResponseBody> response = call.execute();
        if (response.isSuccess()) {
            return jsonToObject(response.body().string());
        }
        LOGGER.warn(response.errorBody().string());
        Plugin<T> plugins = new Plugin<>();
        plugins.setErrorMessage(response.errorBody().string());
        return plugins;
    }

    @Override
    public Plugin<T> queryOne(String pluginId) throws IOException {
        Call<ResponseBody> call = pluginConnector.queryOne(pluginId);
        Response<ResponseBody> response = call.execute();
        if (response.isSuccess()) {
            return jsonToObject(response.body().string());
        }
        LOGGER.warn(response.errorBody().string());
        Plugin<T> plugins = new Plugin<>();
        plugins.setErrorMessage(response.errorBody().string());
        return plugins;
    }

    @Override
    public Plugins<T> query(Plugin<T> plugin) throws IOException {
        Call<ResponseBody> call = pluginConnector.query(plugin.toMap());
        Response<ResponseBody> response = call.execute();
        if (response.isSuccess()) {
            return jsonToList(response.body().string());
        }
        LOGGER.warn(response.errorBody().string());
        return null;
    }

    @Override
    public Plugins<T> querySpecificApi(String apiId, Plugin<T> plugin) throws IOException {
        Call<ResponseBody> call = pluginConnector.querySpecificApi(apiId, plugin == null ? null : plugin.toMap());
        Response<ResponseBody> response = call.execute();
        if (response.isSuccess()) {
            return jsonToList(response.body().string());
        }
        LOGGER.warn(response.errorBody().string());
        return null;
    }

    @Override
    public Plugin<T> querySpecificApiAndPlugin(String apiId) throws IOException {
        Plugin<T> plugin = new Plugin<>();
        plugin.setName(getPluginName());
        Call<ResponseBody> call = pluginConnector.querySpecificApi(apiId, plugin.toMap());
        Response<ResponseBody> response = call.execute();
        if (response.isSuccess()) {
            Plugins<T> plugins = jsonToList(response.body().string());
            if (CollectionUtils.isNotEmpty(plugins.getData())) {
                return plugins.getData().get(0); // 算坑么 ?
            }
        } else {
            LOGGER.warn(response.errorBody().string());
        }
        return null;
    }

    @Override
    public Plugin<T> update(String pluginId, Plugin<T> plugin) throws IOException {
        String apiId = plugin.getApiId();
        if (isBlank(apiId)) {
            apiId = getApiIdByPluginId(pluginId);
        }
        Call<ResponseBody> call = pluginConnector.update(pluginId, apiId, plugin);
        Response<ResponseBody> response = call.execute();
        if (response.isSuccess()) {
            return jsonToObject(response.body().string());
        }
        LOGGER.warn(response.errorBody().string());
        Plugin<T> plugins = new Plugin<>();
        plugins.setErrorMessage(response.errorBody().string());
        return plugins;
    }

    @Override
    public void delete(String pluginId) throws IOException {
        delete(null, pluginId);
    }

    @Override
    public void delete(final String apiId, String pluginId) throws IOException {
        Asserts.checkNotBlank(pluginId, "插件 ID 不能为空");
        pluginConnector.delete(isBlank(apiId) ? getApiIdByPluginId(pluginId) : apiId, pluginId).execute();
    }

    @Override
    public List<String> enabledPlugins() throws IOException {
        Call<EnabledPlugins> call = pluginConnector.queryEnabled();
        Response<EnabledPlugins> response = call.execute();
        if (response.isSuccess()) {
            return response.body().getEnabledPlugins();
        }
        LOGGER.warn(response.errorBody().string());
        return Lists.newArrayList();
    }
}
