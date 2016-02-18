package com.blueocn.api.vo;

import lombok.Builder;
import lombok.Getter;

/**
 * Title: MessageVo
 * Description: 页面消息实体
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-02-18 17:33
 */
@Getter
@Builder
public class MessageVo {

    /**
     * 对应页面消息框的 html class
     */
    private String type = "info";

    /**
     * 页面图标属性
     */
    private String icon;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 消息框是否有关闭按钮
     */
    private boolean close = true;
}
