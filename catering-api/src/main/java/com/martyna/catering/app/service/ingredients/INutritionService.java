package com.martyna.catering.app.service.dishes;

import com.martyna.catering.app.entity.ingredients.Nutrition;

public interface INutritionService {

    Nutrition getByName(String name);
}
