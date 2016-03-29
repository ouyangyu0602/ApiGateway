package com.blueocn.api.controller.rest.admin;

import com.blueocn.api.controller.rest.AbstractResponseController;
import com.blueocn.api.kong.model.consumers.OAuth2;
import com.blueocn.api.response.RestfulResponse;
import com.blueocn.api.service.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Title: RestApplicationController
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-03-09 18:23
 */
@RestController
@RequestMapping("admin/application")
public class RestApplicationController extends AbstractResponseController {

    @Autowired
    private OAuthService oAuthService;

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public RestfulResponse save(@RequestParam("consumerId") String consumerId, OAuth2 oAuth2) {
        return oAuthService.save(consumerId, oAuth2);
    }

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public List<OAuth2> queryOAuthList(OAuth2 oAuth2) {
        return oAuthService.queryAll(oAuth2);
    }

    @RequestMapping(value = "delete/{applicationId}")
    public RestfulResponse delete(@PathVariable("applicationId") String applicationId) {
        oAuthService.delete(applicationId);
        return new RestfulResponse();
    }
}
