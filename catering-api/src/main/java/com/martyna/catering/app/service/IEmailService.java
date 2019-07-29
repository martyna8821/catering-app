package com.martyna.catering.app.service;

import com.martyna.catering.app.entity.User;

import java.util.Locale;

public interface IEmailService {

    void sendSimpleMessage(String to, String subject, String text);
    void sendPasswordResetToken(String contextPath, Locale locale, String token, User user);
}
