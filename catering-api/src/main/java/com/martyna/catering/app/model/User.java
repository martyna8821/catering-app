package com.martyna.catering.app.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class User {


    private Integer id;

    private String name;

    private String username;

    private String email;


    private String password;

    private Set<Role> roles = new HashSet<>();

}
