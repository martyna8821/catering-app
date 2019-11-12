package com.martyna.catering.app.entity.dishes;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
@Getter
@AllArgsConstructor
public class IngredientNutritionId implements Serializable {

    @Column(name = "ingredient_id")
    private UUID ingredientId;

    @Column(name = "nutrition_id")
    private UUID nutritionId;

    private IngredientNutritionId() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        IngredientNutritionId that = (IngredientNutritionId) o;
        return Objects.equals(ingredientId, that.ingredientId) &&
                Objects.equals(nutritionId, that.nutritionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredientId, nutritionId);
    }
}
