package pl.martyna.catering.app.service.recipe.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.martyna.catering.app.entity.recipe.Recipe;
import pl.martyna.catering.app.entity.recipe.RecipeIngredient;
import pl.martyna.catering.app.repository.recipe.IRecipeRepository;
import pl.martyna.catering.app.service.recipe.IRecipeIngredientService;
import pl.martyna.catering.app.service.recipe.IRecipeService;
import pl.martyna.catering.app.service.recipe.IRecipeStepService;

import java.util.List;
import java.util.Set;

@Service
public class RecipeService implements IRecipeService {

    private IRecipeRepository recipeRepository;
    private IRecipeIngredientService recipeIngredientService;

    @Autowired
    public RecipeService(IRecipeRepository recipeRepository,
                         IRecipeIngredientService recipeIngredientService){

        this.recipeRepository = recipeRepository;
        this.recipeIngredientService = recipeIngredientService;
    }

    public RecipeService() {

    }

    @Override
    public Recipe save(Recipe recipe) {
        recipe.setMealWeight(calculateWeight(recipe.getIngredients()));
        recipe.setCaloricValue(calculateCaloricValue(recipe.getIngredients(), recipe.getMealWeight()));

        Recipe savedRecipe = this.recipeRepository.save(recipe);

        savedRecipe.getIngredients().forEach(ingredient -> {
            this.recipeIngredientService.save(ingredient);
        });

        return savedRecipe;
    }

    public int calculateWeight(Set<RecipeIngredient> ingredients){

        return ingredients.stream()
                        .mapToInt(RecipeIngredient::getValue)
                        .sum();
    }

    public double calculateCaloricValue(Set<RecipeIngredient> ingredients, int mealWeight){

       return ingredients.stream().mapToDouble( recipeIngredient ->
                       Double.parseDouble(recipeIngredient.getIngredient().getCaloricValue())
                       * recipeIngredient.getValue()
                       / 100)
                      .sum() * 100 / mealWeight;
    }

    @Override
    public List<Recipe> getAll() {
        return this.recipeRepository.findAll();
    }
}
