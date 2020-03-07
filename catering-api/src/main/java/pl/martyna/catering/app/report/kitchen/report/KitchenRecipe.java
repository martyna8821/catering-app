package pl.martyna.catering.app.report.kitchen.report;

import lombok.Data;
import pl.martyna.catering.app.dto.resource.RecipeIngredientResource;
import pl.martyna.catering.app.dto.resource.RecipeStepResource;
import pl.martyna.catering.app.entity.recipe.MealType;

import java.util.HashSet;
import java.util.Set;

@Data
public class KitchenRecipe {

    private String name;
    private Set<RecipeIngredientResource> ingredients =new HashSet<>();;
    private Set<RecipeStepResource> recipeSteps = new HashSet<>();;
}
