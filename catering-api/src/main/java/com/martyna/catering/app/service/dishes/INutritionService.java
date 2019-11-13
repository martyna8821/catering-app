package com.martyna.catering.app.service.dishes;

import com.martyna.catering.app.entity.dishes.Nutrition;

import java.util.Optional;

public interface INutritionService {

    Nutrition getByName(String name);
}
