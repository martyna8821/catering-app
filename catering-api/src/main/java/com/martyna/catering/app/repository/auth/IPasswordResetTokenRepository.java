package com.martyna.catering.app.repository.auth;

import com.martyna.catering.app.entity.PasswordResetToken;
import com.martyna.catering.app.entity.Role;
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
