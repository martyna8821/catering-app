package com.martyna.catering.app.entity.dishes;


import com.martyna.catering.app.entity.users.Role;
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
    UUID id;

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
    Set<String> allergens = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "ingredient_brands", joinColumns = @JoinColumn(name = "ingredient_id"))
    @Column(name = "brands")
    Set<String> brands = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "ingredient_labels", joinColumns = @JoinColumn(name = "ingredient_id"))
    @Column(name = "labels")
    Set<String> labels = new HashSet<>();


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
        //his.nutrition.add(new IngredientNutrition(this, nutrition, value));
    }

}
