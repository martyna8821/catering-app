package com.martyna.catering.app.dto;

import com.martyna.catering.app.entity.Address;
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
 //   private Address address;
    private String[] roles;
}
