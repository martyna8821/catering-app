package com.martyna.catering.app.service;

import com.martyna.catering.app.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import java.util.Locale;

@Component
public class EmailService  implements IEmailService {

    @Autowired
    public JavaMailSender emailSender;

    @Override
    public void sendSimpleMessage(
            String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    @Override
    public void sendPasswordResetToken(String contextPath, Locale locale, String token, User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Retrieve your password");
        //TODO hash token
        String url = "localhost:4200/reset-password&token=" + token;
        message.setText(String.format("Kliknij link:\n%s .\n", url));
        emailSender.send(message);
    }


}

