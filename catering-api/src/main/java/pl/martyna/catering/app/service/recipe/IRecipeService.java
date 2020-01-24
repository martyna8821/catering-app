package pl.martyna.catering.app.service.recipe;

import pl.martyna.catering.app.entity.recipe.Recipe;
import pl.martyna.catering.app.entity.recipe.RecipeIngredient;

import java.util.List;
import java.util.Set;

public interface IRecipeService {

    Recipe save(Recipe recipe);
    List<Recipe> getAll();

    public int calculateWeight(Set<RecipeIngredient> ingredients);
    public double calculateCaloricValue(Set<RecipeIngredient> ingredients, int mealWeight);

    }
