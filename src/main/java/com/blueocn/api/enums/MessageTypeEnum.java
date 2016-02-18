package com.blueocn.api.enums;

/**
 * Title: MessageType
 * Description: 页面消息类型定义, 其属性为基本 Key, 由 Velocity 模板解析
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-02-18 16:01
 */
public enum MessageTypeEnum {
    ERROR("danger"),
    INFO("info"),
    SUCCESS("success"),
    WARN("warning");

    private String type;

    MessageTypeEnum(String type) {
        this.type = type;
    }

    /**
     * @return 获取该消息类型对应的type
     */
    @Override
    public String toString() {
        return type;
    }
}
