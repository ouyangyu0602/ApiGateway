package com.blueocn.api.controller.rest.user;

import com.blueocn.api.controller.rest.AbstractResponseController;
import com.blueocn.api.response.RestfulResponse;
import com.blueocn.api.response.UserResponse;
import com.blueocn.api.service.UserService;
import com.blueocn.api.service.ValidateService;
import com.blueocn.api.vo.UserVo;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.blueocn.api.support.utils.Assert.checkNotBlank;
import static com.google.common.base.Preconditions.checkArgument;

/**
 * Title: RegisterController
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-01-07 21:32
 */
@RestController
@RequestMapping("user")
public class RestRegisterController extends AbstractResponseController {

    @Autowired
    private UserService userService;

    @Autowired
    private ValidateService validateService;

    @RequestMapping("register")
    public RestfulResponse register(UserVo userVo) {
        try {
            checkUserVo(userVo);
            UserResponse response = userService.register(userVo);
            if (response.isSuccess() && response.getUserId() != null) {
                validateService.sendValidateEmail(userVo, response.getUserId());
                return new RestfulResponse<String>();
            }
            return new RestfulResponse<String>("用户注册失败");
        } catch (IllegalArgumentException e) {
            return handleException(e);
        }
    }

    @RequestMapping(value = "register/check", method = RequestMethod.GET)
    public Map<String, Boolean> checkExistUser(
        @RequestParam(value = "userIdentity", required = false) String userIdentity,
        @RequestParam(value = "userEmail", required = false) String userEmail) {

        boolean valid = true;
        if (userService.checkExistUser(userIdentity, userEmail)) {
            valid = false;
        }
        Map<String, Boolean> result = Maps.newHashMapWithExpectedSize(1);
        result.put("valid", valid);
        return result;
    }

    private void checkUserVo(UserVo userVo) {
        checkNotBlank(userVo.getUserEmail(), "用户邮箱");
        checkNotBlank(userVo.getUserIdentity(), "登录名");
        checkNotBlank(userVo.getUserName(), "用户名");
        checkNotBlank(userVo.getUserPassword(), "用户密码");
        checkNotBlank(userVo.getUserPasswordConfirm(), "用户确认密码");
        checkArgument(userVo.getUserPassword().equals(userVo.getUserPasswordConfirm()),
            "密码与确认密码不一致");
    }
}
