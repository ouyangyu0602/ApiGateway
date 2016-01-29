package com.blueocn.api.support;

import javax.servlet.http.HttpServletRequest;

/**
 * Title: 请求类型判断工具
 * Description: 判断请求是不是一个Ajax请求, 根据请求头的字段判断
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-15 16:31
 */
public class AjaxUtils {
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestedWith = request.getHeader("X-Requested-With");
        return requestedWith != null && "XMLHttpRequest".equals(requestedWith);
    }
}
