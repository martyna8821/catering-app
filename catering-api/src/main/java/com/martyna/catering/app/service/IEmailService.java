package com.martyna.catering.app.service;

public interface IEmailService {
    void sendSimpleMessage(String to, String subject, String text);
}
