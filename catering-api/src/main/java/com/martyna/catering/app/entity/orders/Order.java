package com.martyna.catering.app.entity.orders;

import com.martyna.catering.app.entity.diets.Diet;
import com.martyna.catering.app.entity.users.User;
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
    private LocalDate beginningDate;

    @Column(name = "ending_date")
    private LocalDate endingDate;

    @ManyToOne
    private User client;

    @ManyToOne
    private Diet diet;

}
