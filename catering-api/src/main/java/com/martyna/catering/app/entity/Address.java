package com.martyna.catering.app.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Table(name="addresses")
@Getter @Setter
public class Address {

    @Id
    @Column(name="id")
    private UUID id;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "zip_code")
    private String zipCode;

    @OneToOne
    @MapsId
    private User user;
}
