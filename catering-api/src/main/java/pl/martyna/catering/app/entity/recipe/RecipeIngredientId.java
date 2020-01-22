package pl.martyna.catering.app.entity.recipe;

import lombok.Data;
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
@NoArgsConstructor
@Embeddable
public class RecipeIngredientId implements Serializable {

    @Column(name = "ingredient")
    private UUID ingredient;

    @Column(name = "recipe")
    private  UUID recipe;

    RecipeIngredientId(UUID ingredient, UUID recipe) {
        this.ingredient = ingredient;
        this.recipe = recipe;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj )
            return true;

        if(!(obj instanceof RecipeIngredientId))
            return false;

        RecipeIngredientId that = (RecipeIngredientId) obj;

        return Objects.equals(ingredient, that.ingredient)
                && Objects.equals(recipe, that.recipe);
    }

    @Override
    public int hashCode(){
        return Objects.hash(ingredient, recipe);
    }
}

