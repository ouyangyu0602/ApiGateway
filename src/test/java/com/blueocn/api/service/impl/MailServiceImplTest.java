package com.blueocn.api.service.impl;

import com.blueocn.api.BaseTest;
import com.blueocn.api.service.MailService;
import com.blueocn.api.vo.MailVo;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class MailServiceImplTest extends BaseTest {

    @Autowired
    private MailService mailService;

    @Test
    public void testSendMail() throws Exception {
        mailService.sendMail(buildMail());
        Thread.sleep(20 * 1000L);
    }

    private MailVo buildMail() {
        MailVo mail = new MailVo();

        Map<String, Object> vo = Maps.newHashMap();
        vo.put("test", "XXXXXX");
        mail.setViewObjects(vo);
        mail.setToUsers("995405013@qq.com");
        mail.setMailTemplate("register.vm");
        mail.setSubject("你能看到这封邮件么?");
        mail.setCcUsers("363103802@qq.com");
        mail.setFrom("noreply@yufan.me");
        return mail;
    }
}
