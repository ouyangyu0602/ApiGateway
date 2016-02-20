package com.blueocn.api.controller.rest.api;

import com.blueocn.api.kong.model.Api;
import com.blueocn.api.kong.model.Apis;
import com.blueocn.api.response.RestfulResponse;
import com.blueocn.api.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Title: RestApiController
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-02-19 16:03
 */
@RestController
@RequestMapping("api")
public class RestApiController {

    @Autowired
    private ApiService apiService;

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public RestfulResponse<String> save(Api api) {
        return apiService.save(api);
    }

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public Apis queryApiList(Api api) {
        return apiService.queryAll(api);
    }
}
