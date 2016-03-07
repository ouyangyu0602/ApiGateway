package com.blueocn.api.controller.rest;

import com.blueocn.api.response.RestfulResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Title: RestOAuth2Controller
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-03-07 16:37
 */
@RestController
public class RestOAuth2Controller extends AbstractResponseController {

    @RequestMapping(value = "approval", method = RequestMethod.POST)
    public RestfulResponse oAuthApproval() {

        return null;
    }
}
