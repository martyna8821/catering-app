package com.martyna.catering.app.entity.ingredients;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name= "ingredients_nutrition")
@NoArgsConstructor
public class IngredientNutrition implements Serializable {

  @EmbeddedId
  private IngredientNutritionId id;

   @MapsId("ingredient")
   @ManyToOne
   private Ingredient ingredient;

   @MapsId("nutrition")
   @ManyToOne
   private Nutrition nutrition;

    @Column(name = "value")
    private double value;

    public IngredientNutrition(Ingredient ingredient, Nutrition nutrition, double value) {
       this.setIngredient(ingredient);
       this.setNutrition(nutrition);
       this.value = value;
       this.id = new IngredientNutritionId(ingredient.getId(), nutrition.getId());
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj )
            return true;

        if(!(obj instanceof IngredientNutrition))
            return false;

        IngredientNutrition that = (IngredientNutrition) obj;

        return Objects.equals(ingredient.getId(), that.ingredient.getId())
                && Objects.equals(nutrition.getId(), that.nutrition.getId())
                && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode(){
        return Objects.hash(ingredient.getId(),
                nutrition.getId(), value);
    }
}
