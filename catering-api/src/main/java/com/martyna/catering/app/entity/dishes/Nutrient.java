package com.martyna.catering.app.entity.dishes;

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

    @ManyToOne
    @JoinColumn(name = "measurement_unit_id", referencedColumnName = "measurement_unit_id")
    private MeasurementUnit unit;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "nutrient_details_id", referencedColumnName = "nutrient_details_id")
    private NutrientDetails nutrientDetails;

}
