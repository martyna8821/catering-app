package com.martyna.catering.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {

    private String street;
    private String houseNumber;
    private String city;
    private String zipCode;
}
