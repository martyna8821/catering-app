package com.martyna.catering.app.service.dishes;

import com.martyna.catering.app.repository.dishes.INutrientDetailsRepository;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NutrientDetailsService implements INutrientDetailsService {

    INutrientDetailsRepository nutrientDetailsRepository;

    @Autowired
    public NutrientDetailsService(INutrientDetailsRepository nutrientDetailsRepository){

        this.nutrientDetailsRepository = nutrientDetailsRepository;
    }
}
