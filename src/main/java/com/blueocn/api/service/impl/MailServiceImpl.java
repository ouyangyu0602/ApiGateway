package com.blueocn.api.service.impl;

import com.blueocn.api.service.CommonTaskExecutorService;
import com.blueocn.api.service.MailService;
import com.blueocn.api.vo.MailVo;
import com.google.common.base.Charsets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;

import static org.apache.commons.collections.MapUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Title: MailServiceImpl
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-01-09 01:02
 */
@Service
public class MailServiceImpl implements MailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    private CommonTaskExecutorService commonTaskExecutorService;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private VelocityEngine velocityEngine;

    @Override
    public void sendMail(final MailVo mail) {
        final Runnable task = () -> {
            MimeMessage mineMessage = mailSender.createMimeMessage();
            buildMimeMessage(mail, mineMessage);
            mailSender.send(mineMessage);
        };
        commonTaskExecutorService.executeTask(task);
    }

    private void buildMimeMessage(final MailVo mail, MimeMessage mimeMessage) {
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        try {
            // 添加邮件内容
            if (isNotBlank(mail.getMailTemplate())) {
                mimeMessageHelper
                    .setText(resolveMailContent(mail.getMailTemplate(), mail.getViewObjects()), true);
            }
            if (isNotBlank(mail.getPlainText())) {
                mimeMessageHelper.setText(mail.getPlainText(), true);
            }
            // 添加附件
            if (isNotEmpty(mail.getAttachments())) {
                for (Map.Entry<String, InputStreamSource> attachment : mail.getAttachments()
                    .entrySet()) {
                    mimeMessageHelper.addAttachment(attachment.getKey(), attachment.getValue());
                }
            }
            // 添加主题
            if (isNotBlank(mail.getSubject())) {
                mimeMessageHelper.setSubject(mail.getSubject());
            }
            // Bcc
            if (CollectionUtils.isNotEmpty(mail.getBccUsers())) {
                mimeMessageHelper.setBcc((String[]) mail.getBccUsers().toArray());
            }
            // Cc
            if (CollectionUtils.isNotEmpty(mail.getCcUsers())) {
                mimeMessageHelper.setCc((String[]) mail.getCcUsers().toArray());
            }
            // to
            if (CollectionUtils.isNotEmpty(mail.getToUsers())) {
                mimeMessageHelper.setTo((String[]) mail.getToUsers().toArray());
            }
            // reply
            if (isNotBlank(mail.getReplyTo())) {
                mimeMessageHelper.setReplyTo(mail.getReplyTo());
            }
            // from
            if (isNotBlank(mail.getFrom())) {
                mimeMessageHelper.setFrom(mail.getFrom());
            }
        } catch (MessagingException e) {
            LOGGER.info("", e);
        }
    }

    private String resolveMailContent(String templateFileName, Map<String, Object> model) {
        return VelocityEngineUtils
            .mergeTemplateIntoString(velocityEngine, "template/" + templateFileName,
                Charsets.UTF_8.displayName(), model);
    }
}
