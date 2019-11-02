package com.martyna.catering.app.service.dishes;

import com.martyna.catering.app.repository.dishes.IIngredientTypeRepository;
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
