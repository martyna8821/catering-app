package com.martyna.catering.app.service.ingredients.impl;

import com.martyna.catering.app.repository.ingredients.IIngredientTypeRepository;
import com.martyna.catering.app.service.ingredients.IIngredientTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientTypeService implements IIngredientTypeService {

    IIngredientTypeRepository ingredientTypeRepository;

    @Autowired
    public IngredientTypeService(IIngredientTypeRepository ingredientTypeRepository){
        this.ingredientTypeRepository  = ingredientTypeRepository;
    }
}
