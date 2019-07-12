package com.martyna.catering.app.security.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter @Setter
@NoArgsConstructor
public class RegisterRequest {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Set<String> roles;
}
