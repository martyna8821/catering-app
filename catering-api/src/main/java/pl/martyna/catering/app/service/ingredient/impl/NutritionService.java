package pl.martyna.catering.app.service.ingredient.impl;

import pl.martyna.catering.app.entity.ingredient.Nutrition;
import pl.martyna.catering.app.exception.ResourceNotFoundException;
import pl.martyna.catering.app.repository.ingredient.INutritionRepository;
import pl.martyna.catering.app.service.ingredient.INutritionService;
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
