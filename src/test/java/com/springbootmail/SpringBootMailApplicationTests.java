package com.springbootmail;

import org.assertj.core.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootMailApplication.class)
public class SpringBootMailApplicationTests {

    @Autowired
    JavaMailSender javaMailSender;

    /**
     * 发送带有附件的邮件：
     * 实际使用过程中，可能会带上附件或是使用邮件模板，这时候就需要 MimeMessage 来设置复杂一些的邮件内容。
     *
     * @throws Exception
     */
    @Test
    public void sendSimpleMail() throws Exception{
        /*SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("164425286@qq.com");
        message.setTo("164425286@qq.com");
        message.setSubject("主题：简单邮件");
        message.setText("测试邮件内容");*/

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("164425286@qq.com");
        helper.setTo("164425286@qq.com");
        helper.setSubject("主题：有附件");
        helper.setText("有附件的邮件");

        FileSystemResource file = new FileSystemResource(new File("D:/weixin.jpg"));
        helper.addAttachment("附件-1.jpg", file);
        helper.addAttachment("附件-2.jpg", file);

        /**
         * =====准备发送邮件：2019-03-22T10:08:02.464
         * =====发送邮件结束。2019-03-22T10:08:27.235
         */
        System.err.println("=====准备发送邮件：" + DateUtil.formatAsDatetimeWithMs(new Date()));
        javaMailSender.send(mimeMessage);
        System.err.println("=====发送邮件结束。" + DateUtil.formatAsDatetimeWithMs(new Date()));
    }

}
