package pl.martyna.catering.app.dto.input;

import lombok.Data;
import pl.martyna.catering.app.dto.resource.AddressResource;
import pl.martyna.catering.app.entity.auth.Address;

@Data
public class UserUpdateInput {

    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phoneNumber;
    private Address address;
}
