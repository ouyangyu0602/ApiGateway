package com.blueocn.api.service.impl;

import com.blueocn.api.kong.KongConfig;
import com.blueocn.api.kong.client.OAuth2Client;
import com.blueocn.api.kong.client.PluginClient;
import com.blueocn.api.kong.model.Plugin;
import com.blueocn.api.kong.model.consumers.OAuth2;
import com.blueocn.api.response.RestfulResponse;
import com.blueocn.api.service.OAuthService;
import com.blueocn.api.support.utils.Asserts;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.collections.CollectionUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.*;

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

    private static final Pattern URI_PATTERN = Pattern.compile("http[s]?://[^/]+");

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
    public boolean isValidRedirectUri(String clientId, String redirectUri) throws IOException {
        OAuth2 oAuth2App = getOAuth2App(clientId);
        if (oAuth2App != null && isNotBlank(oAuth2App.getRedirect_uri())) {
            Matcher save = URI_PATTERN.matcher(oAuth2App.getRedirect_uri());
            Matcher pass = URI_PATTERN.matcher(redirectUri);
            if (save.find() && pass.find()) {
                return endsWithIgnoreCase(save.group(), pass.group());
            }
        }
        return false;
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
    public RestfulResponse authorize(String clientId, String apiName, String userId, String scope)
        throws IOException {
        String provision_key = queryProvisionKey(apiName);
        Map<String, String> params = Maps.newHashMapWithExpectedSize(5);
        params.put("client_id", clientId);
        params.put("response_type", "code");
        params.put("scope", scope);
        params.put("authenticated_userid", userId);
        params.put("provision_key", provision_key);
        return oAuth2Client.authorize(params, config.getKongHost());
    }

    @Override
    public OAuth2 queryOne(String id) {
        if (isNotBlank(id)) {
            OAuth2 param = new OAuth2();
            param.setId(id);
            try {
                List<OAuth2> oAuth2s = oAuth2Client.query(param);
                if (oAuth2s != null && oAuth2s.size() == 1) {
                    return oAuth2s.get(0);
                }
            } catch (IOException e) {
                LOGGER.warn("", e);
            }
        } else {
            LOGGER.info("查询 ID 为空, 请校验查询参数.");
        }
        return null;
    }

    @Override
    public RestfulResponse save(String consumerId, OAuth2 oAuth2) {
        Preconditions.checkNotNull(oAuth2, "保存应用信息不能为空.");
        Asserts.checkNotBlank(consumerId, "应用对应的开发者信息不能为空.");
        OAuth2 newOAuth2;
        try {
            if (isBlank(oAuth2.getId())) {
                newOAuth2 = oAuth2Client.add(consumerId, oAuth2);
            } else {
                newOAuth2 = oAuth2Client.update(consumerId, oAuth2.getId(), oAuth2);
            }
        } catch (IOException e) {
            LOGGER.warn("", e);
            return new RestfulResponse(e.getMessage());
        }
        if (newOAuth2 != null && isBlank(newOAuth2.getErrorMessage())) {
            return new RestfulResponse(newOAuth2.getErrorMessage());
        }
        return new RestfulResponse("应用保存失败");
    }

    @Override
    public List<OAuth2> queryAll(OAuth2 oAuth2) {
        OAuth2 queryOAuth = oAuth2 == null ? new OAuth2() : oAuth2;
        queryOAuth.setSize(getOAuth2Amount(oAuth2));
        try {
            return oAuth2Client.query(oAuth2);
        } catch (IOException e) {
            LOGGER.info("", e);
        }
        return Lists.newArrayList();
    }

    /**
     * 查询所有应用的数量
     *
     * @param oAuth2 查询参数
     */
    private Integer getOAuth2Amount(OAuth2 oAuth2) {
        try {
            OAuth2 oAuth = oAuth2 == null ? new OAuth2() : oAuth2;
            oAuth.setSize(1);
            return oAuth2Client.totalSize(oAuth);
        } catch (IOException e) {
            LOGGER.info("", e);
        }
        return null;
    }

    /**
     * 从 API的名称获取它对应的 oAuth2 的provision_key
     *
     * @param apiName API ID 或者 API Name
     * @return provision_key
     */
    private String queryProvisionKey(String apiName) throws IOException {
        Plugin plugin = pluginClient.querySpecificApiAndPlugin(apiName, "oauth2");
        return plugin.getConfig().get("provision_key").toString(); // TODO 这句话有坑需要重构.
    }
}
