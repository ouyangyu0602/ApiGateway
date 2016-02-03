package com.blueocn.api.support.utils;

import com.blueocn.api.vo.MailVo;
import com.blueocn.api.vo.UserVo;

/**
 * Title: ValidateEmailUtils
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-01-16 01:15
 */
public final class EmailUtils {

    private EmailUtils() {
        // No Construct
    }

    public static MailVo getRegisterEmail(UserVo userVo, String validateCode, String systemBaseUri, String mailAddress) {
        MailVo mailVo = new MailVo();
        mailVo.setMailTemplate("register.vm");
        mailVo.setViewObjects("buttonMessage", "激活您的邮箱");
        mailVo.setViewObjects("url", systemBaseUri + "/validate/mail/" + validateCode);
        mailVo.setSubject("尊敬的用户" + userVo.getUserName() + "您好, 请激活您的邮箱");
        mailVo.setToUsers(userVo.getUserEmail());
        mailVo.setFrom(mailAddress);
        return mailVo;
    }
}
