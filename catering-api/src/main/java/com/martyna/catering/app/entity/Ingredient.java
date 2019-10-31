package com.martyna.catering.app.entity;


import io.micrometer.core.instrument.Measurement;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @Column(name = "ingredient_id")
    UUID id;

    @Column(name = "name")
    private String name;

    //@TODO check onetomany and one to one mappings
    @OneToMany
    private MeasurementUnit measurementUnit;

}
