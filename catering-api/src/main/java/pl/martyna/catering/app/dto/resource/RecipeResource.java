package pl.martyna.catering.app.dto.resource;

import lombok.Data;
import lombok.Getter;
import pl.martyna.catering.app.entity.recipe.MealType;
import pl.martyna.catering.app.entity.recipe.RecipeIngredient;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
public class RecipeResource implements Serializable {

    private String id;
    private String name;
    private List<MealType> mealTypes;
    private Set<RecipeIngredientResource> ingredients;
    private Set<RecipeStepResource> recipeSteps;
    private Integer mealWeight;
    private Integer caloricValue;
}
