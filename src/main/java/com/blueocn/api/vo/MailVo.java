package com.blueocn.api.vo;

import com.google.common.collect.Maps;
import org.springframework.core.io.InputStreamSource;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Title: MailVo
 * Description: 用户邮件发送实体, 需要定义发送模板(mailTemplate) 或者发送HTML文本(plainText).
 * 如果定义发送模板, 需要定义模板于webapp/WEB-INF/views/mail 下, 且页面变量使用 viewObjects 传递.
 * 如果定义发送HTML文本(plainText), 且定义了发送模板. 则 plainText 会覆盖发送模板的内容.
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-01-09 00:53
 */
public class MailVo implements Serializable {
    private static final long serialVersionUID = -2022577473670092831L;

    /**
     * 你想发送的附件列表
     */
    private Map<String, InputStreamSource> attachments;

    /**
     * Velocity 的模板名称, eg. register.vm
     */
    private String mailTemplate;

    /**
     * 视图实体解析映射
     */
    private Map<String, Object> viewObjects = Maps.newHashMap();

    /**
     * 纯文本邮件信息, 如果不想定义 mailTemplate, 请将你想传输的 HTML 转义 设置与此处
     */
    private String plainText;

    /**
     * 邮件发送主题
     */
    private String subject;

    /**
     * 暗抄送的用户们
     */
    private List<String> bccUsers;

    /**
     * 明抄送用户们
     */
    private List<String> ccUsers;

    /**
     * 发送的用户们
     */
    private List<String> toUsers;

    /**
     * 回信邮箱
     */
    private String replyTo;

    /**
     * 发件人
     */
    private String from;

    public Map<String, InputStreamSource> getAttachments() {
        return attachments;
    }

    public String getMailTemplate() {
        return mailTemplate;
    }

    public String getPlainText() {
        return plainText;
    }

    public String getSubject() {
        return subject;
    }

    public List<String> getBccUsers() {
        return bccUsers;
    }

    public List<String> getCcUsers() {
        return ccUsers;
    }

    public List<String> getToUsers() {
        return toUsers;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public String getFrom() {
        return from;
    }

    public void setMailTemplate(String mailTemplate) {
        this.mailTemplate = mailTemplate;
    }

    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setBccUsers(List<String> bccUsers) {
        this.bccUsers = bccUsers;
    }

    public void setBccUsers(String... bccUsers) {
        checkArgument(bccUsers.length > 0, "暗抄送的用户不能为空");
        this.bccUsers = Arrays.asList(bccUsers);
    }

    public void setCcUsers(List<String> ccUsers) {
        this.ccUsers = ccUsers;
    }

    public void setCcUsers(String... ccUsers) {
        checkArgument(ccUsers.length > 0, "抄送的用户不能为空");
        this.ccUsers = Arrays.asList(ccUsers);
    }

    public void setToUsers(List<String> toUsers) {
        this.toUsers = toUsers;
    }

    public void setToUsers(String... toUsers) {
        checkArgument(toUsers.length > 0, "发送的用户不能为空");
        this.toUsers = Arrays.asList(toUsers);
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setAttachments(Map<String, InputStreamSource> attachments) {
        this.attachments = attachments;
    }

    public Map<String, Object> getViewObjects() {
        return viewObjects;
    }

    public void setViewObjects(Map<String, Object> viewObjects) {
        this.viewObjects = viewObjects;
    }

    public void setViewObjects(String key, Object value) {
        if (viewObjects == null) {
            synchronized (this) {
                viewObjects = Maps.newHashMap();
            }
        }
        viewObjects.put(key, value);
    }
}
