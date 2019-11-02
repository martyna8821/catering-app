package com.martyna.catering.app.service.users;

import com.martyna.catering.app.entity.users.PasswordResetToken;
import com.martyna.catering.app.entity.users.User;

import java.util.Optional;

public interface IPasswordResetTokenService {

    PasswordResetToken createTokenForUser(User user);
    void delete(String token);
    Optional<User> getUserFromToken(String token);

}
