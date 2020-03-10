package pl.martyna.catering.app.report.daily.kitchenreport.utils;

import lombok.Data;
import pl.martyna.catering.app.dto.resource.RecipeIngredientResource;
import pl.martyna.catering.app.dto.resource.RecipeStepResource;

import java.util.HashSet;
import java.util.Set;

@Data
public class KitchenReportRecipe {

    private String name;
    private Set<RecipeIngredientResource> ingredients =new HashSet<>();
    private Set<RecipeStepResource> recipeSteps = new HashSet<>();
}
