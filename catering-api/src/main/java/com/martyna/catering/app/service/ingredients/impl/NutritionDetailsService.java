package com.martyna.catering.app.service.ingredients.impl;

import com.martyna.catering.app.repository.ingredients.INutritionDetailsRepository;
import com.martyna.catering.app.service.dishes.INutritionDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NutritionDetailsService implements INutritionDetailsService {

    INutritionDetailsRepository nutritionDetailsRepository;

    @Autowired
    public NutritionDetailsService(INutritionDetailsRepository nutritionDetailsRepository){

        this.nutritionDetailsRepository = nutritionDetailsRepository;
    }
}
