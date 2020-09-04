package com.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;


/**
 * @ author     :LianZheng
 * @ Description:邮件工具类（非静态）
 * @ Date       :2020/8/26
*/
@Component
public class MailUtils {

    public static final Logger logger = LoggerFactory.getLogger(MailUtils.class);

    @Autowired
    JavaMailSender mailSender;// spring提供邮件发送类

    @Value("${spring.mail.username}")
    private String from;

    /**
     * @ author     :LianZheng
     * @ Description:纯文本邮件
     * @ Date       :2020/8/26
    */
    @Async   //  异步
    public void sendSimpleMail(String to, String subject, String content) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);//收信人
        message.setSubject(subject);//主题
        message.setText(content);//内容
        message.setFrom(from);//发信人

        mailSender.send(message);
    }

    /**
     * @ author     :LianZheng
     * @ Description:html邮件
     * @ Date       :2020/8/26
     */
    @Async   //  异步
    public void sendHtmlMail(String to, String subject, String content) {

        logger.info("发送HTML邮件开始：{},{},{}", to, subject, content);
        //使用MimeMessage，MIME协议
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper;
        //MimeMessageHelper帮助我们设置更丰富的内容
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);//true代表支持html
            mailSender.send(message);
            logger.info("发送HTML邮件成功");
        } catch (MessagingException e) {
            logger.error("发送HTML邮件失败：", e);
        }
    }

    /**
     * @ author     :LianZheng
     * @ Description:带附件邮件
     * @ Date       :2020/8/26
     */
    @Async   //  异步
    public void sendAttachmentMail(String to, String subject, String content, String filePath) {

        logger.info("发送带附件邮件开始：{},{},{},{}", to, subject, content, filePath);
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, true);
            //true代表支持多组件，如附件，图片等
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = file.getFilename();
            helper.addAttachment(fileName, file);//添加附件，可多次调用该方法添加多个附件
            mailSender.send(message);
            logger.info("发送带附件邮件成功");
        } catch (MessagingException e) {
            logger.error("发送带附件邮件失败", e);
        }


    }
    /**
     * @ author     :Lyl
     * @ Description:带附件邮件
     */
    @Async   //  异步
    public boolean sendAttachmentMailDDU(String to, String subject, String content, String filePath) {

        logger.info("发送带附件邮件开始：{},{},{},{}", to, subject, content, filePath);
        MimeMessage message = mailSender.createMimeMessage();
        boolean succeed;
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, true);
            //true代表支持多组件，如附件，图片等
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            FileSystemResource file = new FileSystemResource(new File(filePath+"/receiver_mail/CustomsFile.pdf"));
            String fileName = file.getFilename();
            helper.addAttachment(fileName, file);
            FileSystemResource file1 = new FileSystemResource(new File(filePath+"/receiver_mail/POA.pdf"));
            String fileName1 = file1.getFilename();
            helper.addAttachment(fileName1, file1);
            mailSender.send(message);
            logger.info("发送带附件邮件成功");
            succeed=true;
        } catch (MessagingException e) {
            succeed=false;
            logger.error("发送带附件邮件失败", e);
        }
        return succeed;

    }

}
