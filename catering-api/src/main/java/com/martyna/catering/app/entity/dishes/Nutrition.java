package com.martyna.catering.app.entity.dishes;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "nutrition")
@NaturalIdCache
@org.hibernate.annotations.Cache( usage = CacheConcurrencyStrategy.READ_WRITE)
public class Nutrition {

    @Id
    private UUID id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "measurement_unit_id", referencedColumnName = "measurement_unit_id")
    private MeasurementUnit unit;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "nutrient_details_id", referencedColumnName = "nutrient_details_id")
    private NutritionDetails nutrientDetails;

}
