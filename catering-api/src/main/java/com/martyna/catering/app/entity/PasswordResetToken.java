package com.martyna.catering.app.entity;

import lombok.Cleanup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "password_reset_token")
public class PasswordResetToken {

    //TODO 48h
    private static final int EXPIRATION_TIME = 60 * 48;

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column
    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @Column
    private LocalDateTime expiryDate;

    public PasswordResetToken(User user, String token){
        this.id = UUID.randomUUID();
        this.token = token;
        this.user = user;
        this.expiryDate = LocalDateTime.now().plusMinutes(EXPIRATION_TIME);
    }

}
