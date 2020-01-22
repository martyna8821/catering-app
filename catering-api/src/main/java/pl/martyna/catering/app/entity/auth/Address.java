package pl.martyna.catering.app.entity.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name="addresses")
@Getter @Setter
@NoArgsConstructor
public class Address implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name="address_id")
    private UUID id;

    @Column(name = "street")
    private String street;

    @Column(name = "house_number",
            length = 20)
    private String houseNumber;

    @Column(name = "city")
    private String city;

    @Column(name = "zip_code",
            length = 10)
    private String zipCode;
}
