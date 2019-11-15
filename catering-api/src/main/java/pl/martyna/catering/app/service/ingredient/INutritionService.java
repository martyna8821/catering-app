package pl.martyna.catering.app.service.ingredient;

import pl.martyna.catering.app.entity.ingredient.Nutrition;

public interface INutritionService {

    Nutrition getByName(String name);
}
