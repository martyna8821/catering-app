package com.martyna.catering.app.service.dishes;

import com.martyna.catering.app.repository.dishes.IIngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService implements IIngredientService{

    IIngredientRepository ingredientRepository;


    @Autowired
    public IngredientService(IIngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }
}
