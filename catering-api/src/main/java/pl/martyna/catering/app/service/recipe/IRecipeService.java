package pl.martyna.catering.app.service.recipe;

import pl.martyna.catering.app.entity.recipe.Recipe;

import java.util.List;

public interface IRecipeService {

    Recipe save(Recipe recipe);
    List<Recipe> getAll();

}
