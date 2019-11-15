package pl.martyna.catering.app.service.mail;

import pl.martyna.catering.app.entity.auth.User;

import java.util.Locale;

public interface IEmailService {

    void sendSimpleMessage(String to, String subject, String text);
    void sendPasswordResetToken(String contextPath, Locale locale, String token, User user);
}
