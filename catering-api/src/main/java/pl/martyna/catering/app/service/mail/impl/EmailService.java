package pl.martyna.catering.app.service.mail.impl;

import pl.martyna.catering.app.entity.auth.User;
import pl.martyna.catering.app.security.jwt.JwtAuthTokenFilter;
import pl.martyna.catering.app.service.mail.IEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Locale;

@Service
public class EmailService  implements IEmailService {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthTokenFilter.class);

    private JavaMailSender emailSender;

    @Autowired
    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

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
        String url = "http://localhost:4200/reset-password/" + token;
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            String htmlMessage = String.format("<h3>Kliknij w link, aby zmienic haslo:<h3>\n" +
                   " <form action=\"%s\">\n" +
                    "    <input type=\"submit\" value=\"Link\" />\n" +
                    "</form> \n", url);

            mimeMessage.setContent(htmlMessage, "text/html");
            helper.setTo(user.getEmail());
            helper.setSubject("Retrieve your password");
        }
        catch(MessagingException ex){
            logger.error(ex.getMessage());
        }
        emailSender.send(mimeMessage);
    }
}

