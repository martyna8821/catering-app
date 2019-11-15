package pl.martyna.catering.app.service.ingredient.impl;

import pl.martyna.catering.app.repository.ingredient.INutritionDetailsRepository;
import pl.martyna.catering.app.service.ingredient.INutritionDetailsService;
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
