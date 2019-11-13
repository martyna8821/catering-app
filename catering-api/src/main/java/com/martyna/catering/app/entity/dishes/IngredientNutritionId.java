package com.martyna.catering.app.entity.dishes;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
@Getter
@AllArgsConstructor
public class IngredientNutritionId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @ManyToOne
    @JoinColumn(name = "nutrition_id")
    private Nutrition nutrition;

    private IngredientNutritionId() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        IngredientNutritionId that = (IngredientNutritionId) o;
        return Objects.equals(ingredient.id, that.ingredient.id) &&
                Objects.equals(nutrition.getId(), that.nutrition.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredient.id, nutrition.getId());
    }
}
