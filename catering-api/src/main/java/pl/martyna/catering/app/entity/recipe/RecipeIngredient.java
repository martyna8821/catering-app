package pl.martyna.catering.app.entity.recipe;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.martyna.catering.app.entity.ingredient.Ingredient;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="recipes_ingredients")
public class RecipeIngredient {

    @EmbeddedId
    private RecipeIngredientId id;

    @MapsId("ingredient")
    @ManyToOne
    private Ingredient ingredient;

    @MapsId("recipe")
    @ManyToOne
    private Recipe recipe;

    @Column(name = "value")
    private int value;

    public RecipeIngredient(Ingredient ingredient, Recipe recipe,
                            int value) {
        this.setIngredient(ingredient);
        this.setRecipe(recipe);
        this.value = value;
        this.id = new RecipeIngredientId(ingredient.getId(), recipe.getId());
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj )
            return true;

        if(!(obj instanceof RecipeIngredient))
            return false;

        RecipeIngredient that = (RecipeIngredient) obj;

        return Objects.equals(ingredient.getId(), that.ingredient.getId())
                && Objects.equals(recipe.getId(), that.recipe.getId())
                && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode(){
        return Objects.hash(ingredient.getId(),
                recipe.getId(), value);
    }

}
