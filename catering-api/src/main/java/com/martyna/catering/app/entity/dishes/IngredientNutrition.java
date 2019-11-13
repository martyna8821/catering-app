package com.martyna.catering.app.entity.dishes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name= "ingredients_nutrition")
@NoArgsConstructor
public class IngredientNutrition implements Serializable {


    @EmbeddedId
    private IngredientNutritionId id;

   @ManyToOne
   @MapsId("ingredient")
   private Ingredient ingredient;

   @ManyToOne
   @MapsId("nutrition")
   private Nutrition nutrition;

    @Column(name = "value")
    private double value;

    public IngredientNutrition(Ingredient ingredient, Nutrition nutrition, double value) {
        this.setIngredient(ingredient);
        this.setNutrition(nutrition);
        this.value = value;
    }

}
