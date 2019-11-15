package pl.martyna.catering.app.service.ingredient.impl;

import pl.martyna.catering.app.repository.ingredient.IIngredientTypeRepository;
import pl.martyna.catering.app.service.ingredient.IIngredientTypeService;
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
