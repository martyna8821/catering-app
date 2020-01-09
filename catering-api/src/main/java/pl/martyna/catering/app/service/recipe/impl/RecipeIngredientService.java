package pl.martyna.catering.app.service.recipe.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.martyna.catering.app.entity.recipe.RecipeIngredient;
import pl.martyna.catering.app.repository.recipe.IRecipeIngredientRepository;
import pl.martyna.catering.app.service.recipe.IRecipeIngredientService;

import java.util.UUID;

@Service
public class RecipeIngredientService implements IRecipeIngredientService {

    IRecipeIngredientRepository recipeIngredientRepository;

    @Autowired
    public RecipeIngredientService(IRecipeIngredientRepository recipeIngredientRepository){
        this.recipeIngredientRepository = recipeIngredientRepository;
    }

    @Override
    public RecipeIngredient save(RecipeIngredient recipeIngredient) {
        return this.recipeIngredientRepository.save(recipeIngredient);
    }
}
