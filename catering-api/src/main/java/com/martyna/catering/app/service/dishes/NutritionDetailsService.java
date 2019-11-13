package com.martyna.catering.app.service.dishes;

import com.martyna.catering.app.repository.dishes.INutritionDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NutritionDetailsService implements INutritionDetailsService {

    INutritionDetailsRepository nutrientDetailsRepository;

    @Autowired
    public NutritionDetailsService(INutritionDetailsRepository nutrientDetailsRepository){

        this.nutrientDetailsRepository = nutrientDetailsRepository;
    }
}
