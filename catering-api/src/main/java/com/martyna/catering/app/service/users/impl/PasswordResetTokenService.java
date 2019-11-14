package com.martyna.catering.app.service.users.impl;

import com.martyna.catering.app.entity.users.PasswordResetToken;
import com.martyna.catering.app.entity.users.User;
import com.martyna.catering.app.repository.auth.IPasswordResetTokenRepository;
import com.martyna.catering.app.service.users.IPasswordResetTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordResetTokenService implements IPasswordResetTokenService {

    private IPasswordResetTokenRepository passwordTokenRepository;

    @Autowired
    public PasswordResetTokenService(IPasswordResetTokenRepository passwordTokenRepository){
        this.passwordTokenRepository = passwordTokenRepository;
    }

    @Override
    public PasswordResetToken createTokenForUser(User user) {
        String token = UUID.randomUUID().toString();
        PasswordResetToken passwordToken = new PasswordResetToken(user,token);
        return passwordTokenRepository.save(passwordToken);
    }

    @Override
    public void delete(String token) {
        passwordTokenRepository.deleteByToken(token);
    }

    @Override
    public Optional<User> getUserFromToken (String token) {
        return passwordTokenRepository.findByToken(token)
                    .filter(t -> t.getExpiryDate().isAfter(LocalDateTime.now()))
                    .map(PasswordResetToken::getUser);
    }
}
