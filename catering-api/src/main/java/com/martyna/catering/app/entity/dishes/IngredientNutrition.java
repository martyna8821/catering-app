package com.martyna.catering.app.entity.dishes;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name= "ingredients_nutrition")
public class IngredientNutrition {


    @EmbeddedId
    private IngredientNutritionId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ingredientId")
    private Ingredient ingredient;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("nutritionId")
    private Nutrition nutrition;

    @Column(name = "value")
    private double value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        IngredientNutrition that = (IngredientNutrition) o;
        return Objects.equals(ingredient, that.ingredient) &&
                Objects.equals(nutrition, that.nutrition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredient, nutrition);
    }
}
