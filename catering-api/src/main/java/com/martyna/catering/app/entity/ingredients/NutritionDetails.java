package com.martyna.catering.app.entity.ingredients;

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
public class NutritionDetails {

    @Id
    @Column(name = "nutrient_details_id")
    UUID id;

    @Column(name = "description")
    private String description;

    @Column(name = "min_value")
    private int minValue;

    @Column(name = "max_value")
    private int maxValue;
}
