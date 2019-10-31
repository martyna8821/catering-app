package com.martyna.catering.app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "nutrient_details")
public class NutrientDetials {

    @Id
    @Column(name = "nutrient_details_id")
    UUID id;

    @Column(name = "descripption")
    private String description;



    //@TODO min i max value for nutrient detalis, maybe unit?
    //@Column(name = "min_val")
}
