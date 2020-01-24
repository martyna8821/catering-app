package pl.martyna.catering.app.dto.auth;

import lombok.AllArgsConstructor;
import pl.martyna.catering.app.entity.auth.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Address address;
    private Set<String> roles;

}
