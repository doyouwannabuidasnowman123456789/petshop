package com.project.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.project.ecommerce.exeptions.APIException;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender javaMailSender;

    public String sendEmail(String toEmail, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("minhmeomeomun@gmail.com");
            message.setTo(toEmail);
            message.setText(body);
            message.setSubject(subject);
            javaMailSender.send(message);
            return "Check your email to get new password";
        } catch (Exception e) {
            throw new APIException(e.getMessage());
        }
    }
}
