package com.blueocn.api.service.impl;

import com.blueocn.api.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * Title: MailSenderTest
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-01-08 23:51
 */
public class MailSenderTest extends BaseTest {

    @Autowired
    private JavaMailSender mailSender;

    @Test
    public void testSend() throws Exception {
        mailSender.send(simpleMailMessage());
    }

    private SimpleMailMessage simpleMailMessage() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("noreply@yufan.me");
        mailMessage.setTo("995405013@qq.com");
        mailMessage.setSubject("这是一份测试邮件");
        mailMessage.setText("你能看到么?");
        return mailMessage;
    }
}
