package pl.martyna.catering.app.service.users;

import pl.martyna.catering.app.entity.auth.PasswordResetToken;
import pl.martyna.catering.app.entity.auth.User;

import java.util.Optional;

public interface IPasswordResetTokenService {

    PasswordResetToken createTokenForUser(User user);
    void delete(String token);
    Optional<User> getUserFromToken(String token);

}
