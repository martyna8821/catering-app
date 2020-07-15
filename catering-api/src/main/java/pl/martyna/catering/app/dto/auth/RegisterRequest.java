package pl.martyna.catering.app.dto.auth;

import lombok.AllArgsConstructor;
import pl.martyna.catering.app.entity.auth.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotNull
    private String username;
    @Size(min=7)
    private String password;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    @Pattern(regexp = "^.+@.+\\..+$")
    private String email;
    @NotNull
    private String phoneNumber;
    private Address address;
    private Set<String> roles;

}
