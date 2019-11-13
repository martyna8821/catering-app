package com.martyna.catering.app.service.dishes;

import com.martyna.catering.app.entity.dishes.Nutrition;
import com.martyna.catering.app.exception.ResourceNotFoundException;
import com.martyna.catering.app.repository.dishes.INutrientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NutritionService implements INutritionService {

    INutrientRepository nutrientRepository;

    @Autowired
    public NutritionService(INutrientRepository nutrientRepository){
        this.nutrientRepository = nutrientRepository;
    }


    @Override
    public Nutrition getByName(String name) {
       return this.nutrientRepository.getByName(name)
               .orElseThrow(ResourceNotFoundException::new);
    }
}
