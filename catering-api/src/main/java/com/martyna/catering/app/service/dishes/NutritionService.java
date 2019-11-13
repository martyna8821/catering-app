package com.martyna.catering.app.service.dishes;

import com.martyna.catering.app.entity.dishes.Nutrition;
import com.martyna.catering.app.exception.ResourceNotFoundException;
import com.martyna.catering.app.repository.dishes.INutritionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NutritionService implements INutritionService {

    INutritionRepository nutritionRepository;

    @Autowired
    public NutritionService(INutritionRepository nutritionRepository){

        this.nutritionRepository = nutritionRepository;
    }


    @Override
    public Nutrition getByName(String name) {
       return this.nutritionRepository.getByName(name)
               .orElseThrow(ResourceNotFoundException::new);
    }
}
