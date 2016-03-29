package com.blueocn.api.service;

import com.blueocn.api.kong.model.consumers.OAuth2;
import com.blueocn.api.response.RestfulResponse;

import java.io.IOException;
import java.util.List;

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
     * 校验开发者传递的 Client Id 是否有效
     *
     * @param clientId kong 中存储名的应用标识 {@code client_id}
     * @return Client Id 是否存在
     */
    boolean isValidClientId(String clientId) throws IOException;

    /**
     * 校验开发者传点的 Client Id 对应的应用在 Kong 存储的跳转地址
     * 与开发者传递的地址是否一致
     *
     * @param clientId    应用 ID
     * @param redirectUri 跳转地址
     * @return
     */
    boolean isValidRedirectUri(String clientId, String redirectUri) throws IOException;

    /**
     * 通过 Client Id 查询具体应用信息
     *
     * @param clientId kong 中存储名的应用标识 {@code client_id}
     * @return OAuth 应用信息
     * @throws IOException
     */
    OAuth2 getOAuth2App(String clientId) throws IOException;

    /**
     * Kong oAuth2 认证
     *
     * @param clientId 应用开发者App Key
     * @param apiName  API 的英文标识名称, 非 ID
     * @param userId   用户ID
     * @param scopes   认证资源
     */
    RestfulResponse authorize(String clientId, String apiName, String userId, String scopes)
        throws IOException;

    /**
     * 通过应用 ID 查询OAuth2应用信息
     *
     * @param id 应用ID
     */
    OAuth2 queryOne(String id);

    /**
     * 保存开发者 oAuth2 应用信息
     *
     * @param consumerId 开发者ID
     * @param oAuth2     应用信息
     */
    RestfulResponse save(String consumerId, OAuth2 oAuth2);

    /**
     * 查询复合查询参数的开发者的所有 OAuth 插件信息
     *
     * @param oAuth2 应用信息
     */
    List<OAuth2> queryAll(OAuth2 oAuth2);

    /**
     * 删除应用
     *
     * @param id 应用ID
     */
    void delete(String id);
}
