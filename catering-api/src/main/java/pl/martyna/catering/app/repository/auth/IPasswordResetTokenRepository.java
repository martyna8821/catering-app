package pl.martyna.catering.app.repository.auth;

import pl.martyna.catering.app.entity.auth.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IPasswordResetTokenRepository
        extends JpaRepository<PasswordResetToken, UUID> {

    @Transactional
    void deleteByToken(String token);

    Optional<PasswordResetToken> findByToken(String token);
}
