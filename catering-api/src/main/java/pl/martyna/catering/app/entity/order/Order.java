package pl.martyna.catering.app.entity.order;

import pl.martyna.catering.app.entity.auth.Address;
import pl.martyna.catering.app.entity.diet.Diet;
import pl.martyna.catering.app.entity.auth.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @Column(name = "order_id")
    private UUID id;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "beginning_date")
    private LocalDate startDate;

    @Column(name = "ending_date")
    private LocalDate endDate;

    @ManyToOne
    private User client;

    @ManyToOne
    private Diet diet;

    @ManyToOne
    private Address deliveryAddress;



}
