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
    String LOGIN_URI = "/user/login";

    // 模板标题定义符
    String PAGE_TITLE = "pageTitle";

    // 请求跳转头
    String X_FORWARDED_FOR_HEADER = "x-forwarded-for";

    // 密码和盐值拼接字符串
    String SALT_SPIT_CHAR = "(^_^)";

    String ACTIVE_PROFILE_NAME =
        System.getProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "");

    String SYSTEM_CONF_PROPERTIES =
        "application" + ("".equals(ACTIVE_PROFILE_NAME) ? "" : "-") + ACTIVE_PROFILE_NAME + ".properties";
}
