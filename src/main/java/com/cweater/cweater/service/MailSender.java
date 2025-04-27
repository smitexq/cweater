package com.cweater.cweater.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSender {
    @Value("${spring.mail.userName}")
    private String userName;

    private final JavaMailSender mailSender;

    public MailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void send(String emailTo, String theme, String msg) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(userName);
        message.setTo(emailTo);
        message.setSubject(theme);
        message.setText(msg);

        mailSender.send(message);
    }
}
