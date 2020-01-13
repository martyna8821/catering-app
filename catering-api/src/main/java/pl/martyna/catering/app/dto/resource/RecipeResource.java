package pl.martyna.catering.app.dto.resource;

import lombok.Data;
import lombok.Getter;
import pl.martyna.catering.app.entity.recipe.MealType;
import pl.martyna.catering.app.entity.recipe.RecipeIngredient;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class RecipeResource implements Serializable {

    private String id;
    private String name;
    private Set<MealType> mealTypes = new HashSet<>();;
    private Set<RecipeIngredientResource> ingredients =new HashSet<>();;
    private Set<RecipeStepResource> recipeSteps = new HashSet<>();;
    private Integer mealWeight;
    private Integer caloricValue;
}
