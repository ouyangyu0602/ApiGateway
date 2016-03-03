package com.blueocn.api.service;

import com.blueocn.api.kong.model.Plugin;
import com.blueocn.api.kong.model.configs.OAuth2Config;

import java.util.List;

/**
 * Title: PluginService
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-03-03 11:23
 */
public interface PluginService {

    /**
     * 查询所有启用的插件
     */
    List<String> queryEnabledPlugins();

    /**
     * 查询某个 API 的 OAuth2 插件信息
     * @param apiId API ID
     */
    Plugin<OAuth2Config> queryOAuth2Plugin(String apiId);

    /**
     * 新建或者修改OAuth2 插件信息
     * @param apiId  API ID
     * @param plugin 插件信息
     */
    Plugin<OAuth2Config> saveOAuth2Plugin(String apiId, Plugin<OAuth2Config> plugin);
}
