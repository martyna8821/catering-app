package pl.martyna.catering.app.dto.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressInput {

    private String street;
    private String houseNumber;
    private String city;
    private String zipCode;
}
