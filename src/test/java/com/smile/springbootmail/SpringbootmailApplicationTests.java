package com.smile.springbootmail;

import com.smile.springbootmail.model.User;
import com.smile.springbootmail.service.MailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootmailApplication.class)
class SpringbootmailApplicationTests {

    String FROM = "***@qq.com";
    String TO = "***@163.com";
    String CC = "***@qq.com";
    @Test
    void contextLoads() {

    }

    @Autowired
    MailService mailService;

    @Test
    public void sendSimpleMail() {

        mailService.sendSimpleMail(FROM, TO, CC, "测试邮件主题~~~", "测试邮件内容haha");
    }

    @Test
    public void sendAttachMail() {
        File file = new File("/Users/tony/Desktop/旅行/1208/IMG_2970.JPG");
        mailService.sendAttachFileMail(FROM, TO, CC, "邮件主题@", "@邮件内容", file);
    }

    @Test
    public void sendImageMail() {
        String[] srcPaths = {"/Users/tony/Desktop/design/vip_forever.jpg", "/Users/tony/Desktop/design/vip_season.jpg"};
        String[] resIds = {"p01", "p02"};
        String content = "<div>hello, 这是一封带图片的邮件：图片1：<div><img src='cid:p01'/></div>图片2：<div><img src='cid:p02'/></div></div>";
        mailService.sendMailWithImage(FROM, TO, CC, "邮件主题(带图片)", content, srcPaths, resIds);
    }

    @Test
    public void sendHtmlMail() {
        try {
            Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
            ClassLoader loader = SpringbootmailApplication.class.getClassLoader();
            configuration.setClassLoaderForTemplateLoading(loader, "ftl");
            Template template = configuration.getTemplate("mailtemplate.ftl");
            StringWriter writer = new StringWriter();
            User user = new User();
            user.setGender("男");
            user.setUsername("江南一点雨");
            template.process(user, writer);
            mailService.sendHtmlMail(FROM, TO, CC, "测试邮件(html)", writer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }

    @Autowired
    TemplateEngine templateEngine;

    @Test
    public void sendHtmlMailThymeleaf() {
        Context context = new Context();
        context.setVariable("username", "sang");
        context.setVariable("gender", "男");
        String mail = templateEngine.process("mailtemplate.html", context);
        mailService.sendHtmlMail(FROM, TO, CC, "邮件（HTML）", mail);
    }

}
