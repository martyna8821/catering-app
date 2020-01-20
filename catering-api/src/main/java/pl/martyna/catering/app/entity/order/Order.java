package pl.martyna.catering.app.entity.order;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import pl.martyna.catering.app.entity.auth.Address;
import pl.martyna.catering.app.entity.diet.Diet;
import pl.martyna.catering.app.entity.auth.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "order_id")
    @Type(type="pg-uuid")
    private UUID id;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "beginning_date")
    private LocalDate startDate;

    @Column(name = "ending_date")
    private LocalDate endDate;

    @Column(name = "caloric_version",
            length = 50)
    private String caloricVersion;

    @ManyToOne
    private User client;

    @Column(name = "delivery_time")
    private LocalTime deliveryTime;

    @Column(name = "price")
    private int price;

    @ManyToOne
    private Diet diet;

    @ManyToOne
    private Address deliveryAddress;

}
