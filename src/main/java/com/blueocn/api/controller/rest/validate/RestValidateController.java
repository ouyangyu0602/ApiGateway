package com.blueocn.api.controller.rest.validate;

import com.blueocn.api.controller.rest.AbstractResponseController;
import com.blueocn.api.response.RestfulResponse;
import com.blueocn.api.response.ValidateResponse;
import com.blueocn.api.service.UserService;
import com.blueocn.api.service.ValidateService;
import com.blueocn.api.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Title: RestValidateController
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-01-16 01:09
 */
@RestController
@RequestMapping("validate")
public class RestValidateController extends AbstractResponseController {

    @Autowired
    private UserService userService;

    @Autowired
    private ValidateService validateService;

    @RequestMapping(value = "mail/retry", method = RequestMethod.POST)
    public RestfulResponse<String> resentMailValidate(@RequestParam("userId") Long userId,
        @RequestParam("validateCode") String validateCode) {

        if (isValidateUser(userId, validateCode)) {
            UserVo userVo = userService.queryUserById(userId);
            validateService.sendValidateEmail(userVo, userId);
        }
        return new RestfulResponse<String>("用户权限不符, 你无权验证邮箱.");
    }

    private boolean isValidateUser(Long userId, String validateCode) {
        ValidateResponse response = validateService.isCodeValidate(validateCode);
        return response.isSuccess() && userId.equals(response.getUserId());
    }
}
