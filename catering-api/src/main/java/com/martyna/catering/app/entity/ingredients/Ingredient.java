package com.martyna.catering.app.entity.ingredients;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "ingredients")
public class Ingredient implements Serializable {

    @Id
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private double quantity;

    @ManyToOne
    @JoinColumn(name = "measurement_unit_id", referencedColumnName = "measurement_unit_id")
    private MeasurementUnit measurementUnit;

    @ElementCollection
    @CollectionTable(name = "ingredient_allergens", joinColumns = @JoinColumn(name = "ingredient_id"))
    @Column(name = "allergens")
    private Set<String> allergens = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "ingredient_brands", joinColumns = @JoinColumn(name = "ingredient_id"))
    @Column(name = "brands")
    private Set<String> brands = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "ingredient_labels", joinColumns = @JoinColumn(name = "ingredient_id"))
    @Column(name = "labels")
    private Set<String> labels = new HashSet<>();


    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "nutrition"
    )
    private Set<IngredientNutrition> nutrition = new HashSet<>();

    public void addNutrition(Nutrition nutrition, double value ){
        IngredientNutrition ingredientNutrition = new IngredientNutrition(
                this, nutrition, value);
        this.nutrition.add(ingredientNutrition);
    }
}
