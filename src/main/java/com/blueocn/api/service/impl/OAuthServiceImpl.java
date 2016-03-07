package com.blueocn.api.service.impl;

import com.blueocn.api.kong.client.OAuth2Client;
import com.blueocn.api.kong.model.consumers.OAuth2;
import com.blueocn.api.response.RestfulResponse;
import com.blueocn.api.service.OAuthService;
import com.blueocn.api.support.utils.Asserts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

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
    public RestfulResponse authorize(String clientId, Long userId, String scopes) {
        return null;
    }
}
