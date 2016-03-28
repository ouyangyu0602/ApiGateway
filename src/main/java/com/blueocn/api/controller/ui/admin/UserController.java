package com.blueocn.api.controller.ui.admin;

import com.blueocn.api.controller.ui.AbstractUIController;
import com.blueocn.api.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

import static com.blueocn.api.enums.MessageTypeEnum.ERROR;
import static com.blueocn.api.support.Constants.LOGIN_URI;
import static com.blueocn.api.support.session.SessionManager.INSTANCE;
import static com.blueocn.api.support.utils.Asserts.checkNotBlank;

/**
 * Title: LoginController
 * Description: 用户登录控制器
 * // TODO 初版去掉了数据库的部分, 存在配置文件里面, 以后修改.
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-01-04 22:05
 */
@Controller
@RequestMapping("admin")
public class UserController extends AbstractUIController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Value("${admin.username}")
    private String username;

    @Value("${admin.password}")
    private String password;

    @RequestMapping(value = "user/login", method = RequestMethod.GET)
    public String login(Model model) { // NOSONAR
        return "admin/user/login";
    }

    @RequestMapping(value = "user/login", method = RequestMethod.POST)
    public String login(UserVo userVo, HttpServletRequest request, Model model) {
        try {
            checkNotBlank(userVo.getUserIdentity(), "用户登录名不能为空");
            checkNotBlank(userVo.getUserPassword(), "用户密码不能为空");

            UserVo existUser = getUserVo(userVo.getUserIdentity(), userVo.getUserPassword());
            if (existUser != null) {
                INSTANCE.login(existUser, request.getSession());
                return "redirect:/admin/index";
            }
        } catch (Exception e) {
            LOGGER.warn("", e);
            setMessage(model, ERROR, e.getMessage());
        }
        return login(model);
    }

    private UserVo getUserVo(String userIdentity, String userPassword) {
        if (StringUtils.equals(username, userIdentity) &&
            StringUtils.equals(password, userPassword)) {
            UserVo userVo = new UserVo();
            userVo.setUserIdentity(username);
            userVo.setUserName(username);
            userVo.setUserPassword(password);
            return userVo;
        }
        return null;
    }

    @RequestMapping(value = "user/logout")
    public String logout(HttpServletRequest request) {
        INSTANCE.logout(request.getSession());
        return "redirect:" + LOGIN_URI;
    }
}
