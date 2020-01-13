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

       return ingredients.stream().mapToDouble( recipeIngredient ->
           Double.parseDouble(recipeIngredient.getIngredient().getCaloricValue())
               * recipeIngredient.getValue() /100
       ).sum();
    }

    @Override
    public List<Recipe> getAl() {
        return this.recipeRepository.findAll();
    }
}
