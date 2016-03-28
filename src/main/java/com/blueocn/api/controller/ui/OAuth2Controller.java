package com.blueocn.api.controller.ui;

import com.blueocn.api.enums.MessageTypeEnum;
import com.blueocn.api.service.ApiService;
import com.blueocn.api.service.MatrixService;
import com.blueocn.api.service.OAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Title: OAuth2Controller
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-02-25 10:49
 */
@Controller
@RequestMapping("oauth2")
public class OAuth2Controller extends AbstractUIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OAuth2Controller.class);

    @Autowired
    private MatrixService matrixService;

    @Autowired
    private OAuthService oAuthService;

    @Autowired
    private ApiService apiService;

    /**
     * 登录页面, 接收来自开发者应用请求的 clientId, 返回登录页面
     */
    @RequestMapping(value = "authorize", method = RequestMethod.GET)
    public String oAuthLogin(@RequestParam(value = "client_id", required = false) String clientId,
        @RequestParam(value = "apiName", required = false) String apiName,
        @RequestParam(value = "state", required = false) String state,
        @RequestParam(value = "redirect_uri", required = false) String redirectUri, Model model) {
        try {
            if (isBlank(clientId)) {
                setMessage(model, MessageTypeEnum.ERROR, "Client Id (client_id) 不能为空");
            } else if (isBlank(apiName)) {
                setMessage(model, MessageTypeEnum.ERROR, "Api Name (apiName) 不能为空");
            } else if (!oAuthService.isValidClientId(clientId)) {
                setMessage(model, MessageTypeEnum.ERROR, "Client Id (client_id) 参数无效, 请联系您的应用开发者.");
            } else if (!apiService.isApiNameExist(apiName)) {
                setMessage(model, MessageTypeEnum.ERROR, "Api Name (apiName) 参数无效, 请联系您的应用开发者.");
            } else if (!oAuthService.isValidRedirectUri(clientId, redirectUri)) {
                setMessage(model, MessageTypeEnum.ERROR, "跳转地址不合法, 请联系您的应用开发者.");
            }
        } catch (IOException e) {
            LOGGER.info("认证服务异常, 请刷新重试.", e);
            setMessage(model, MessageTypeEnum.ERROR, e.getMessage());
        }
        return "oauth2/authorize";
    }

    @RequestMapping(value = "authorize", method = RequestMethod.POST)
    public String oAuthCertificate(@RequestParam("username") String username,
        @RequestParam("password") String password, @RequestParam("client_id") String clientId,
        @RequestParam(value = "apiName", required = false) String apiName,
        @RequestParam(value = "scope", required = false) String scope,
        @RequestParam(value = "state", required = false) String state,
        @RequestParam(value = "redirect_uri") String redirectUri, Model model)
        throws IOException {
        String userId = matrixService.login(username, password);
        model.addAttribute("client_id", clientId);
        model.addAttribute("scope", scope);
        model.addAttribute("api_name", apiName);
        model.addAttribute("state", state);
        model.addAttribute("api_name", apiName);
        model.addAttribute("redirect_uri", redirectUri);
        if (isNotBlank(userId)) {
            model.addAttribute("login_user_id", userId);
            model.addAttribute("application", oAuthService.getOAuth2App(clientId));
            // 登录成功, 返回用户确认页面
            return "oauth2/approval";
        } else {
            setMessage(model, MessageTypeEnum.ERROR, "用户登录失败, 请查证后重试.");
            return "oauth2/authorize";
        }
    }
}
