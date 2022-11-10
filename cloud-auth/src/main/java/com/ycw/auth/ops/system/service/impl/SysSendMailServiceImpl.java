package com.ycw.auth.ops.system.service.impl;

import com.ycw.auth.ops.system.service.SysSendMailService;
import com.ycw.auth.ops.system.util.PreUtil;
import com.ycw.common.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Map;

/**
 * @Author: ycw
 * @Description: TODO
 * @DateTime: 2021/9/13 13:17
 **/
@Service
@Slf4j
public class SysSendMailServiceImpl implements SysSendMailService {
//    @Value("${file.save-file-path}")
//    private String saveFilePath;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Autowired
    private JavaMailSender mailSender;
    //注入spring发送邮件的对象
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendSimpleMail(String to, HttpServletRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        //设定邮件参数
        //发送者
        message.setFrom(fromEmail);
        //接收者
        message.setTo(to);
        //主题
        message.setSubject("修改pre系统用户邮箱");
        //邮件内容
        // 验证码
        String code = PreUtil.codeGen(4);
        message.setText("验证码:" + code + "。" + "你正在使用邮箱验证码修改功能，该验证码仅用于身份验证，请勿透露给他人使用");
        // 发送邮件
        javaMailSender.send(message);
        request.getSession().setAttribute(Constants.RESET_MAIL, code.toLowerCase());
        try {
            javaMailSender.send(message);        //执行发送
        } catch (Exception e) {
            log.error("邮件发送失败！");
        }
    }

    @Override
    public void sendAttachmentMail(Map<String, Object> map) {
        if (!CollectionUtils.isEmpty(map)) {
            for (Map.Entry entryMap : map.entrySet()) {
                try {
                    //邮件对象
                    MimeMessage mimeMessage = mailSender.createMimeMessage();
                    //解决中文名乱码
                    System.setProperty("mail.mime.splitlongparameters", "false");
                    //邮件处理对象
                    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");

                    //发件人
                    helper.setFrom(fromEmail);

                    //收件人
                    helper.setTo((String) entryMap.getKey());

                    //主题
                    helper.setSubject("hello!");

                    //文本内容
                    helper.setText("当月经营指导书和报表！", false);

                    //设置文件资源
//                    helper.addAttachment((String) entryMap.getValue(), new File(saveFilePath + entryMap.getValue()));
                    //发送
                    mailSender.send(mimeMessage);

                    log.info("单附件邮件发送成功!");
                } catch (MessagingException e) {
                    log.error("单附件邮件发送失败：{}", e);
                }
            }
        }
    }

    @Override
    public void sendAttachmentMail(String email, String subject, String text, Map<String, Object> map) {
        String saveFilePath = "saveFilePath";
        String fileName = "fileName";
        try {
            //邮件对象
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            //解决中文名乱码
            System.setProperty("mail.mime.splitlongparameters", "false");
            //邮件处理对象
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");

            //发件人
            helper.setFrom(fromEmail);

            //收件人
            helper.setTo(email);

            //主题
            helper.setSubject(subject);

            //文本内容
            helper.setText(text, false);

            //设置文件资源
            helper.addAttachment((String) map.get(fileName), new File((String) map.get(saveFilePath)));
            System.out.println(fileName);
            //发送
            mailSender.send(mimeMessage);
            log.info("【单附件邮件发送成功】收件人：{} 主题：{} 文本内容：{} 文件路径：{}", email, text, text);
        } catch (MessagingException e) {
            log.error("单附件邮件发送失败：{}", e);
        }
    }
}

