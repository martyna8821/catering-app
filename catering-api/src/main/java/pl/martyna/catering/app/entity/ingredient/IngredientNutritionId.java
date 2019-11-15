package pl.martyna.catering.app.entity.ingredient;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class IngredientNutritionId implements Serializable {

    @Column(name = "ingredient")
    private UUID ingredient;

    @Column(name = "nutrition")
    private  UUID nutrition;

    public IngredientNutritionId(UUID ingredient, UUID nutrition) {
        this.ingredient = ingredient;
        this.nutrition = nutrition;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj )
            return true;

        if(!(obj instanceof IngredientNutritionId))
            return false;

        IngredientNutritionId that = (IngredientNutritionId) obj;

        return Objects.equals(ingredient, that.ingredient)
                && Objects.equals(nutrition, that.nutrition);
            //    && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode(){
        return Objects.hash(ingredient, nutrition);
    }
}
