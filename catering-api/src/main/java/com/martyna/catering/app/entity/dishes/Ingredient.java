package com.martyna.catering.app.entity.dishes;


import com.martyna.catering.app.entity.users.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
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

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "measurement_unit_id", referencedColumnName = "measurement_unit_id")
    private MeasurementUnit measurementUnit;

    @ElementCollection
    @CollectionTable(name = "ingredient_allergens", joinColumns = @JoinColumn(name = "ingredient_id"))
    @Column(name = "allergens")
    Set<String> allergens = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "ingredient_brands", joinColumns = @JoinColumn(name = "ingredient_id"))
    @Column(name = "brands")
    Set<String> brands = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "ingredient_labels", joinColumns = @JoinColumn(name = "ingredient_id"))
    @Column(name = "labels")
    Set<String> labels = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ingredient_ingredient-types",
            joinColumns = @JoinColumn(name = "ingredient_type_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_type_id"))
    private Set<Role> types = new HashSet<>();
}
