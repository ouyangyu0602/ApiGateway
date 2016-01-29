package com.blueocn.api.service;

import com.blueocn.api.vo.MailVo;

/**
 * Title: MailService
 * Description: 通用发件服务
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2016-01-09 00:50
 */
public interface MailService {

    /**
     * 通用邮件发送接口
     *
     * @param mail {@code MailVo}
     * @see MailVo
     */
    void sendMail(final MailVo mail);
}
