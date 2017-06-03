package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


/**
 * Created by OskarPraca on 2017-06-03.
 */

@Service
public class MailSerivce {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String toWho, String message, String title) {
        MimeMessage mail = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(toWho);
            helper.setFrom("oskarbank@gmail.com");
            helper.setSubject(title);
            helper.setText(message, true);
            helper.setReplyTo("oskarbank@gmail.com");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(mail);
    }
}
