package pl.martyna.catering.app.entity.recipe;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;
import java.util.UUID;

@Data
@Embeddable
public class RecipeIngredientId {

    @Column(name = "ingredient")
    private UUID ingredient;

    @Column(name = "recipe")
    private  UUID recipe;

    public RecipeIngredientId(UUID ingredient, UUID recipe) {
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

