package com.smile.springbootmail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

//@Component
public class MailService {

    @Autowired
    JavaMailSender mailSender;

    /**
     * 发送邮件
     * @param from
     * @param to
     * @param cc
     * @param subject
     * @param content
     */
    public void sendSimpleMail(String from, String to, String cc, String subject, String content) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setCc(cc);
        message.setSubject(subject);
        message.setText(content);

        mailSender.send(message);
    }

    /**
     * 发送带附件的邮件
     * @param from
     * @param to
     * @param cc
     * @param subject
     * @param content
     * @param file
     */
    public void sendAttachFileMail(String from, String to, String cc, String subject, String content, File file) {

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setCc(cc);
            helper.setSubject(subject);
            helper.setText(content);
            helper.addAttachment(file.getName(), file);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送正文在图片的邮件
     * @param from
     * @param to
     * @param cc
     * @param subject
     * @param content
     * @param srcPaths
     * @param resIds
     */
    public void sendMailWithImage(String from, String to, String cc, String subject, String content, String[] srcPaths, String[] resIds) {

        if (srcPaths.length != resIds.length) {
            System.out.println("邮件发送失败，图片路径不一致");
            return;
        }
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setCc(cc);
            helper.setSubject(subject);
            helper.setText(content);

            for (int i = 0; i < srcPaths.length; i++) {
                FileSystemResource res = new FileSystemResource(new File(srcPaths[i]));
                helper.addInline(resIds[i], res);
            }
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("邮件发送失败！");
        }
    }

    public void sendHtmlMail(String from, String to, String cc, String subject, String content) {

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setCc(cc);
            helper.setSubject(subject);
            helper.setText(content, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
