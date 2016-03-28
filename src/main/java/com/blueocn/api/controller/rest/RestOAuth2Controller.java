package com.blueocn.api.controller.rest;

import com.blueocn.api.response.RestfulResponse;
import com.blueocn.api.service.OAuthService;
import okhttp3.HttpUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Title: RestOAuth2Controller
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-03-07 16:37
 */
@RestController
@RequestMapping("oauth2")
public class RestOAuth2Controller extends AbstractResponseController {

    @Autowired
    private OAuthService oAuthService;

    @RequestMapping(value = "approval", method = RequestMethod.POST)
    public RestfulResponse oAuthApproval(@RequestParam("client_id") String clientId,
        @RequestParam("api_name") String apiName,
        @RequestParam("login_user_id") String loginUserId,
        @RequestParam(value = "scope", required = false) String scope,
        @RequestParam(value = "state", required = false) String state,
        @RequestParam(value = "redirect_uri") String redirectUri,
        HttpServletResponse response) throws IOException {
        RestfulResponse restfulResponse = oAuthService.authorize(clientId, apiName, loginUserId, scope);
        String code = "";
        if (restfulResponse.isSuccess()) {
            code = getKongCode(restfulResponse.getCode());
        }
        HttpUrl clientRedirect = HttpUrl.parse(redirectUri);
        if (clientRedirect != null) { // Indicate they are valid urls
            HttpUrl.Builder builder = clientRedirect.newBuilder(redirectUri);
            if (isNotBlank(state)) {
                builder.addQueryParameter("state", state);
            }
            if (isNotBlank(code)) {
                builder.addQueryParameter("code", code);
            }
            if (isNotBlank(scope)) {
                builder.addQueryParameter("scope", scope);
            }
            response.sendRedirect(builder.build().toString());
        }
        return restfulResponse;
    }

    /**
     * 从 Kong 认证成功的返回地址中找到 code 参数
     * 解析失败则会返回空
     *
     * 使用 {@link okhttp3.HttpUrl#parse} 实现URL的解析
     * @param kongRedirectUri kong 返回的认证成功地址
     */
    private String getKongCode(String kongRedirectUri) {
        HttpUrl kongCode = HttpUrl.parse(kongRedirectUri);
        if (kongCode != null) {
            return kongCode.queryParameter("code");
        }
        return null;
    }
}
