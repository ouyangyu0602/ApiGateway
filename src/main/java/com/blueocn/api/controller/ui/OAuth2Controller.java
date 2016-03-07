package com.blueocn.api.controller.ui;

import com.blueocn.api.enums.MessageTypeEnum;
import com.blueocn.api.response.RestfulResponse;
import com.blueocn.api.service.MatrixService;
import com.blueocn.api.service.OAuthService;
import com.blueocn.user.entity.ResultMessage;
import com.blueocn.user.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    @Autowired
    private MatrixService matrixService;

    @Autowired
    private OAuthService oAuthService;

//    @Value("${oauth.common.provisionKey}")
//    private String oAuthProvisionKey;
    /**
     * 登录页面, 接收来自开发者应用请求的 clientId, 返回登录页面
     */
    @RequestMapping(value = "login", method = {RequestMethod.GET, RequestMethod.POST})
    public String oAuthLogin(@RequestParam("clientId") String clientId, Model model) {
        if (!oAuthService.validAppKey(clientId)) {
            setMessage(model, MessageTypeEnum.ERROR, "应用 APP Key 失效, 请联系你的应用管理员.");
        }
        return "oauth2/login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String oAuthCertificate(@RequestParam("username") String username,
        @RequestParam("password") String password, @RequestParam("clientId") String clientId,
        @RequestParam(value = "scopes", required = false) String scopes,
        HttpServletResponse response, Model model) throws IOException {
        ResultMessage<String> status = matrixService.login(username, password);
        if (status.success) {
            ResultMessage<UserInfo> userInfo = matrixService.getLoginUserInfo(status.result);
            RestfulResponse redirectUrl = oAuthService.authorize(clientId, userInfo.result.getUserId(), scopes);
            response.sendRedirect(redirectUrl.getMsg());
        } else {
            setMessage(model, MessageTypeEnum.ERROR, status.message);
            model.addAttribute("client_id", clientId);
            return "oauth2/login";
        }
        return "oauth2/certificateFailed";
    }
}
