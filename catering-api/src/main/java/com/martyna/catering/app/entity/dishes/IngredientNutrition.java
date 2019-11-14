package com.martyna.catering.app.entity.dishes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.ExtensionMethod;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name= "ingredients_nutrition")
@NoArgsConstructor
//@IdClass(IngredientNutritionId.class)
public class IngredientNutrition implements Serializable {

  @EmbeddedId
  private IngredientNutritionId id;

   @MapsId("ingredient")
   @ManyToOne
  // @JoinColumn
   private Ingredient ingredient;

   @MapsId("nutrition")
   @ManyToOne
   //@JoinColumn
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
