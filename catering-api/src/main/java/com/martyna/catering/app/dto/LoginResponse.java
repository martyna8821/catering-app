package com.martyna.catering.app.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class LoginResponse {

    @Getter @Setter private String token;
    @Getter private final String type = "Bearer";
    @Getter @Setter private String username;
    @Getter @Setter private String userId;
    @Getter private Collection<? extends GrantedAuthority> authorities;

    public LoginResponse(String token, String username, String userId, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.username = username;
        this.userId = userId;
        this.authorities = authorities;
    }
}
