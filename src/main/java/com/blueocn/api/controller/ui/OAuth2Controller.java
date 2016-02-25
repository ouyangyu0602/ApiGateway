package com.blueocn.api.controller.ui;

import org.springframework.stereotype.Controller;
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

    /**
     * 登录页面, 接收来自开发者应用请求的 client_id, 返回登录页面
     */
    @RequestMapping(value = "login", method = {RequestMethod.GET, RequestMethod.POST})
    public String oAuthLogin(@RequestParam("client_id") String clientId) {
        // TODO 调用 kong api 判断 client_id 是否存在
        return "oauth2/login";
    }

    @RequestMapping(value = "session", method = RequestMethod.POST)
    public String oAuthCertificate(@RequestParam("username") String username,
        @RequestParam("password") String password,
        @RequestParam("client_id") String client_id, HttpServletResponse response)
        throws IOException {
        // TODO 调用用户系统SDK登录
        // TODO 请求 Kong 注册 API, 跳转至 Kong 返回的回调地址
        response.sendRedirect("");
        return "oauth2/certificateFailed";
    }
}
