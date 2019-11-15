package pl.martyna.catering.app.dto.resource;

import lombok.Getter;
import lombok.Setter;
import pl.martyna.catering.app.dto.input.AddressInput;

@Getter
@Setter
public class UserResource {

    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phoneNumber;
    private AddressInput address;
    private String[] roles;
}
