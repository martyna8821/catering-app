package com.martyna.catering.app.service.dishes;

import com.martyna.catering.app.repository.dishes.IRecipeStepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeStepService implements IRecipeStepService {

    IRecipeStepRepository recipeStepRepository;

    @Autowired
    public RecipeStepService(IRecipeStepRepository recipeStepRepository){
        this.recipeStepRepository = recipeStepRepository;
    }

}
