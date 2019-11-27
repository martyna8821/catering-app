package pl.martyna.catering.app.dto.resource;

import lombok.Data;
import org.apache.tomcat.jni.Local;
import pl.martyna.catering.app.entity.auth.Address;
import pl.martyna.catering.app.entity.auth.User;
import pl.martyna.catering.app.entity.diet.Diet;

import java.time.LocalDate;

@Data
public class OrderResource {

    private String id;
    private Diet diet;
    private String caloricVersion;
    private LocalDate startDate;
    private LocalDate endDate;
    private Address deliveryAddress;
    private User client;
    private LocalDate orderDate;
}
