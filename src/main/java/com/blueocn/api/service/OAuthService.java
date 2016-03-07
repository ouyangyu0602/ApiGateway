package com.blueocn.api.service;

import com.blueocn.api.response.RestfulResponse;

/**
 * Title: OAuthService
 * Description: OAuth相关的认证服务, 主要与 Kong 交互
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-02-25 16:24
 */
public interface OAuthService {

    /**
     * 校验开发者传递的 App Key 是否有效
     *
     * @param appKey 对外宣称为 appKey, 实际在 kong 中存储时名为 client_id
     * @return App Key 是否存在与 Kong 上
     */
    boolean validAppKey(String appKey);

    /**
     * Kong oAuth2 认证
     * @param clientId 应用开发者App Key
     * @param userId   用户ID
     * @param scopes   认证资源
     */
    RestfulResponse authorize(String clientId, Long userId, String scopes);
}
