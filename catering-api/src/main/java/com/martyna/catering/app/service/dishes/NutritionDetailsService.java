package com.martyna.catering.app.service.dishes;

import com.martyna.catering.app.repository.dishes.INutrientDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NutritionDetailsService implements INutritionDetailsService {

    INutrientDetailsRepository nutrientDetailsRepository;

    @Autowired
    public NutritionDetailsService(INutrientDetailsRepository nutrientDetailsRepository){

        this.nutrientDetailsRepository = nutrientDetailsRepository;
    }
}
