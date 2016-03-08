package com.blueocn.api.service.impl;

import com.blueocn.api.kong.KongConfig;
import com.blueocn.api.kong.client.OAuth2Client;
import com.blueocn.api.kong.client.PluginClient;
import com.blueocn.api.kong.model.Plugin;
import com.blueocn.api.kong.model.consumers.OAuth2;
import com.blueocn.api.response.RestfulResponse;
import com.blueocn.api.service.OAuthService;
import com.blueocn.api.support.utils.Asserts;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

/**
 * Title: OAuthServiceImpl
 * Description: OAuth 服务实现
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-02-25 16:33
 */
@Service
public class OAuthServiceImpl implements OAuthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OAuthServiceImpl.class);

    @Autowired
    private OAuth2Client oAuth2Client;

    @Autowired
    private KongConfig config;

    @Autowired
    private PluginClient pluginClient;

    @Override
    public boolean isValidClientId(String clientId) throws IOException {
        return getOAuth2App(clientId) != null;
    }

    @Override
    public OAuth2 getOAuth2App(String clientId) throws IOException {
        Asserts.checkNotBlank(clientId, "Client Id 不能为空");
        OAuth2 oAuth2 = new OAuth2();
        oAuth2.setClient_id(clientId);
        try {
            List<OAuth2> list = oAuth2Client.query(oAuth2);
            if (isNotEmpty(list) && list.size() == 1) {
                return list.get(0);
            }
        } catch (IOException e) {
            LOGGER.warn("", e);
            throw new IOException("查询超时, 请刷新页面, 或者重新申请认证.");
        }
        return null;
    }

    @Override
    public RestfulResponse authorize(String clientId, String apiName, String userId,
        String scope) throws IOException {
        String provision_key = queryProvisionKey(apiName);
        Map<String, String> params = Maps.newHashMapWithExpectedSize(5);
        params.put("client_id", clientId);
        params.put("response_type", "code");
        params.put("scope", scope);
        params.put("authenticated_userid", userId);
        params.put("provision_key", provision_key);
        return oAuth2Client.authorize(params, config.getKongHost());
    }

    /**
     * 从 API的名称获取它对应的 oAuth2 的provision_key
     * @param apiName API ID 或者 API Name
     * @return provision_key
     */
    private String queryProvisionKey(String apiName) throws IOException {
        Plugin plugin = pluginClient.querySpecificApiAndPlugin(apiName, "oauth2");
        return plugin.getConfig().get("provision_key").toString(); // TODO 这句话有坑需要重构.
    }
}
