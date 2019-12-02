package pl.martyna.catering.app.service.recipe.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.martyna.catering.app.entity.ingredient.Ingredient;
import pl.martyna.catering.app.entity.ingredient.IngredientNutrition;
import pl.martyna.catering.app.entity.ingredient.Nutrition;
import pl.martyna.catering.app.entity.recipe.Recipe;
import pl.martyna.catering.app.entity.recipe.RecipeIngredient;
import pl.martyna.catering.app.repository.recipe.IRecipeRepository;
import pl.martyna.catering.app.repository.recipe.IRecipeStepRepository;
import pl.martyna.catering.app.service.recipe.IRecipeIngredientService;
import pl.martyna.catering.app.service.recipe.IRecipeService;
import pl.martyna.catering.app.service.recipe.IRecipeStepService;

import java.util.List;
import java.util.Set;

@Service
public class RecipeService implements IRecipeService {

    private IRecipeRepository recipeRepository;
    private IRecipeStepService recipeStepService;
    private IRecipeIngredientService recipeIngredientService;

    @Autowired
    public RecipeService(IRecipeRepository recipeRepository,
                         IRecipeStepService recipeStepService,
                         IRecipeIngredientService recipeIngredientService){
        this.recipeRepository = recipeRepository;
        this.recipeStepService = recipeStepService;
        this.recipeIngredientService = recipeIngredientService;
    }

    @Override
    public Recipe add(Recipe recipe) {

        recipe.setMealWeight(calculateWeight(recipe.getIngredients()));
        recipe.setCaloricValue(calculateCaloricValue(recipe.getIngredients()));

        recipe.getRecipeSteps().forEach(step -> {
            this.recipeStepService.save(step);
        });

        Recipe savedRecipe = this.recipeRepository.save(recipe);

        savedRecipe.getIngredients().forEach(ingredient -> {
            this.recipeIngredientService.save(ingredient);
        });

            return savedRecipe;
    }

    private int calculateWeight(Set<RecipeIngredient> ingredients){

        return ingredients.stream()
                        .mapToInt(RecipeIngredient::getValue)
                        .sum();
    }

    private double calculateCaloricValue(Set<RecipeIngredient> ingredients){
        return ingredients.stream()
                .mapToDouble( ingredient -> {
                    return ingredient.getIngredient()
                            .getNutrition()
                            .stream()
                                .filter( n ->
                                         n.getNutrition()
                                                    .getName()
                                                    .equals("Energia")
                                )
                                .mapToDouble(nutrition ->
                                    nutrition.getValue()*ingredient.getValue()/100
                                ).sum();
                }).sum();
    }

    @Override
    public List<Recipe> getAl() {
        return this.recipeRepository.findAll();
    }
}
