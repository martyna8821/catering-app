package pl.martyna.catering.app.dto.resource;

import lombok.Data;

import java.util.UUID;

@Data
public class AddressResource {

    private UUID id;
    private String street;
    private String houseNumber;
    private String city;
}
