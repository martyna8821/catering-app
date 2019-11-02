package com.martyna.catering.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phoneNumber;
    private AddressDTO address;
    private String[] roles;
}
