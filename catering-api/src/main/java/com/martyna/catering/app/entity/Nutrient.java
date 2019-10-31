package com.martyna.catering.app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "nutrients")
public class Nutrient {

    @Id
    @Column(name = "nutrient_id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @OneToOne
    private NutrientDetials nutrientDetials;

}
