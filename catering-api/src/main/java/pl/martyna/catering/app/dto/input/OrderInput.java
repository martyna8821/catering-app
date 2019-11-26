package pl.martyna.catering.app.dto.input;

import lombok.Data;
import pl.martyna.catering.app.dto.resource.DietResource;
import pl.martyna.catering.app.entity.auth.Address;
import pl.martyna.catering.app.entity.auth.User;
import pl.martyna.catering.app.entity.diet.Diet;

import java.time.LocalDate;

@Data
public class OrderInput {

    private Diet diet;
    private String caloricVersion;
    private LocalDate startDate;
    private LocalDate endDate;
    private Address deliveryAddress;
    private User client;
}
