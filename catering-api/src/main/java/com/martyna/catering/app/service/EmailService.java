package com.martyna.catering.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;

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
    public void sendGeneratedPasswowrd(String userEmail, String password) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userEmail);
        message.setSubject("Retrieve your password");
        message.setText(String.format("Możesz zalogować się do swojego konta za pomocą hasła:\n%s ." +
                "\n Po zalogowaniu do systemu zmień swoje hasło. \n", password));
        emailSender.send(message);
    }


}

