package com.blueocn.api.controller.rest.admin;

import com.blueocn.api.controller.rest.AbstractResponseController;
import com.blueocn.api.kong.model.Api;
import com.blueocn.api.kong.model.Apis;
import com.blueocn.api.response.RestfulResponse;
import com.blueocn.api.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("admin/api")
public class RestApiController extends AbstractResponseController {

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

    @RequestMapping(value = "delete/{apiId}")
    public RestfulResponse<String> delete(@PathVariable("apiId") String apiId) {
        apiService.delete(apiId);
        return new RestfulResponse<>();
    }
}
