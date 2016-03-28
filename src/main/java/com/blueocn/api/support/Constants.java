package com.blueocn.api.support;

import org.springframework.core.env.AbstractEnvironment;

import java.util.regex.Pattern;

/**
 * Title: Constants
 * Description: 基础常量类
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-16 10:53
 */
public interface Constants { // NOSONAR

    // 逗号分割符
    Pattern COMMA_SPLIT_PATTERN = Pattern.compile("\\s*[,]+\\s*");

    Pattern BRACE_SPLIT_PATTERN = Pattern.compile("\\s*(\\{\\S*?\\})+\\s*");

    // 用户Session信息标识
    String LOGIN_USER_IN_SESSION = "current_login_user";

    // 用户页面实体标识
    String LOGIN_USER = "loginUser";

    // 登录页URI
    String LOGIN_URI = "/admin/user/login";

    // 请求跳转头
    String X_FORWARDED_FOR_HEADER = "x-forwarded-for";

    String ACTIVE_PROFILE_NAME =
        System.getProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "");

    String SYSTEM_CONF_PROPERTIES =
        "application" + ("".equals(ACTIVE_PROFILE_NAME) ? "" : "-") + ACTIVE_PROFILE_NAME + ".properties";
}
