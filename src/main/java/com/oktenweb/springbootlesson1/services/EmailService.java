package com.oktenweb.springbootlesson1.services;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
@PropertySource("classpath:application.properties")
public class EmailService {

    private Environment environment;

    private JavaMailSender javaMailSender;

    public void sendEmail(String email, MultipartFile file) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(mimeMessage, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        try {
            mimeMessage.setFrom(environment.getProperty("spring.mail.username"));
            helper.setTo(email);
            helper.setText("Visit our  <a href=https://owu.com.ua>site</a>", true);
            helper.addAttachment(file.getOriginalFilename(), file);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mimeMessage);

    }
}
