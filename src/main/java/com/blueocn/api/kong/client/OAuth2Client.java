package com.blueocn.api.kong.client;

import com.blueocn.api.kong.model.consumers.OAuth2;
import com.blueocn.api.response.RestfulResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Title: OAuth2Client
 * Description: 开发者应用插件调用接口类
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-03-01 17:06
 */
public interface OAuth2Client {

    /**
     * 新增 OAuth2 应用
     *
     * @param consumerId 开发者 ID
     * @param oAuth2     应用信息
     */
    OAuth2 add(String consumerId, OAuth2 oAuth2) throws IOException;

    /**
     * 查询一堆
     *
     * @param oAuth2 查询参数
     */
    List<OAuth2> query(OAuth2 oAuth2) throws IOException;

    /**
     * 查询一堆
     *
     * @param consumerId 开发者 ID
     * @param oAuth2     查询参数
     */
    List<OAuth2> query(String consumerId, OAuth2 oAuth2) throws IOException;

    /**
     * 查询某个应用
     *
     * @param consumerId 开发者 ID
     * @param oauth2Id   应用 ID
     */
    OAuth2 queryOne(String consumerId, String oauth2Id) throws IOException;

    /**
     * 更新某个应用
     *
     * @param consumerId 开发者 ID
     * @param oauth2Id   应用 ID
     * @param oAuth2     {@code OAuth2}
     */
    OAuth2 update(String consumerId, String oauth2Id, OAuth2 oAuth2) throws IOException;

    /**
     * 删除应用
     *
     * @param consumerId 开发者 ID
     * @param oauth2Id   应用 ID
     */
    void delete(String consumerId, String oauth2Id) throws IOException;

    /**
     * 认证应用授权
     *
     * @param params 授权信息参数
     * @param host   授权 API Host
     * @return 请求结果
     */
    RestfulResponse authorize(Map<String, String> params, String host);

    /**
     * 判断基于此种查询条件的应用数量
     *
     * @param oAuth 应用查询条件数据
     */
    Integer totalSize(OAuth2 oAuth) throws IOException;
}
