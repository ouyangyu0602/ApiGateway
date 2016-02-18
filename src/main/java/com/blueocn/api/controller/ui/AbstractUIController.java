package com.blueocn.api.controller.ui;

import com.blueocn.api.enums.MessageTypeEnum;
import com.blueocn.api.support.session.SessionManager;
import com.blueocn.api.vo.MessageVo;
import com.blueocn.api.vo.UserVo;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

import static com.blueocn.api.support.Constants.BRACE_SPLIT_PATTERN;

/**
 * Title: UI控制器基础公共类
 * Description: 定义某些公共方法, 公共属性
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-15 15:54
 */
public abstract class AbstractUIController {

    protected UserVo getLoginUser(HttpServletRequest request) {
        return SessionManager.INSTANCE.getLoginUser(request.getSession());
    }

    protected void setMessage(Model model, MessageTypeEnum type, String message) {
        setMessage(model, type, message, null, true);
    }

    protected void setMessage(Model model, MessageTypeEnum type, String message, String... url) {
        setMessage(model, type, message, null, true, url);
    }

    /**
     * 通用信息设置
     *
     * @param model       {@code Model} Spring MVC 页面模型
     * @param type        消息类型
     * @param message     想要显示的信息. 定义 {content} 为占位符,
     *                    默认替换为连接 {@code <a href="url" target="_blank">content</a>}
     * @param urls        想要显示的 URL 们, 可以没有
     * @param messageIcon 消息图标Class
     * @param closeAble   消息信息是否内关闭
     */
    protected void setMessage(Model model, MessageTypeEnum type, String message, String messageIcon,
        Boolean closeAble, String... urls) {
        if (urls.length != 0) {
            for (String url : urls) {
                if (BRACE_SPLIT_PATTERN.matcher(message).find()) {
                    message = message.replaceFirst("\\{", "<a href=\"" + url + "\" target=\"_blank\">") // NOSONAR
                        .replaceFirst("\\}", "</a>");
                }
            }
        }
        MessageVo vo = MessageVo.builder().type(type.toString()).icon(messageIcon).content(message).close(closeAble).build();
        model.addAttribute("message", vo);
    }
}
