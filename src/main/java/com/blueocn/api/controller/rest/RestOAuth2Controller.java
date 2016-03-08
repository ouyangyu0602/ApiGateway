package com.blueocn.api.controller.rest;

import com.blueocn.api.response.RestfulResponse;
import com.blueocn.api.service.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    public RestfulResponse oAuthApproval(@RequestParam("client_id") String client_id,
        @RequestParam("api_name") String api_name,
        @RequestParam("login_user_id") String login_user_id,
        @RequestParam(value = "scope", required = false) String scope,
        Model model, HttpServletResponse response) throws IOException {
        RestfulResponse restfulResponse = oAuthService.authorize(client_id, api_name, login_user_id, scope);
        if (restfulResponse.isSuccess()) {
            response.sendRedirect(restfulResponse.getCode());
        }
        return restfulResponse;
    }
}
