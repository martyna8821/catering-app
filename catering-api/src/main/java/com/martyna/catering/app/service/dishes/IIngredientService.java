package com.martyna.catering.app.service.dishes;

import com.martyna.catering.app.dto.IngredientToCreate;
import com.martyna.catering.app.entity.dishes.Ingredient;

public interface IIngredientService {

    Ingredient saveIngredient(IngredientToCreate ingredient);

}
