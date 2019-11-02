package com.martyna.catering.app.service.dishes;

import com.martyna.catering.app.repository.dishes.INutrientDetailsRepository;
import com.martyna.catering.app.repository.dishes.INutrientRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NutrientService implements  INutrientService {

    INutrientRepository nutrientRepository;

    @Autowired
    public NutrientService(INutrientRepository nutrientRepository){
        this.nutrientRepository = nutrientRepository;
    }

}
