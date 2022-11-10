package com.ycw.auth.ops.system.service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface SysSendMailService {
    //简单邮件
    void sendSimpleMail(String to, HttpServletRequest request);
    //定时发送带附件邮件
    void sendAttachmentMail(Map<String,Object> map);
    //发送带附件的复杂的邮件
    void sendAttachmentMail(String email, String subject ,String text, Map<String,Object> map);
}
