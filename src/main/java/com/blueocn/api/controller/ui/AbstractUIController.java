package com.blueocn.api.controller.ui;

import com.blueocn.api.support.session.SessionManager;
import com.blueocn.api.vo.UserVo;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

import static com.blueocn.api.support.Constants.PAGE_ERROR_ATTRIBUTE;
import static com.blueocn.api.support.Constants.PAGE_TITLE;

/**
 * Title: UI控制器基础公共类
 * Description: 定义某些公共方法, 公共属性
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-15 15:54
 */
public abstract class AbstractUIController {

    /**
     * 对于显示的页面标题定义方法
     *
     * @param model     {@code Model} Spring MVC 页面模型
     * @param pageTitle 想要设置的标题
     */
    protected void setPageTitle(Model model, String pageTitle) {
        model.addAttribute(PAGE_TITLE, " | " + pageTitle);
    }

    protected UserVo getLoginUser(HttpServletRequest request) {
        return SessionManager.INSTANCE.getLoginUser(request.getSession());
    }

    /**
     * 通用错误信息设置
     *
     * @param model        {@code Model} Spring MVC 页面模型
     * @param errorMessage 错误信息设置
     */
    protected void setErrorMessage(Model model, String errorMessage) {
        model.addAttribute(PAGE_ERROR_ATTRIBUTE, errorMessage);
    }
}
