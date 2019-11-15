package pl.martyna.catering.app.service.recipe.impl;

import pl.martyna.catering.app.repository.recipe.IRecipeStepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.martyna.catering.app.service.recipe.IRecipeStepService;

@Service
public class RecipeStepService implements IRecipeStepService {

    IRecipeStepRepository recipeStepRepository;

    @Autowired
    public RecipeStepService(IRecipeStepRepository recipeStepRepository){
        this.recipeStepRepository = recipeStepRepository;
    }

}
