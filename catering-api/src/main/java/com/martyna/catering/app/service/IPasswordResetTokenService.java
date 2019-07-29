package com.martyna.catering.app.service;

import com.martyna.catering.app.entity.PasswordResetToken;
import com.martyna.catering.app.entity.User;

import java.util.Optional;

public interface IPasswordResetTokenService {

    PasswordResetToken createTokenForUser(User user);
    void delete(String token);
    Optional<User> getUserFromToken(String token);

}
