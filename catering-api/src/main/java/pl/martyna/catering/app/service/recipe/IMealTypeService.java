package pl.martyna.catering.app.service.recipe;

import pl.martyna.catering.app.entity.recipe.MealType;

public interface IMealTypeService {

    MealType findByName(String name);
}
