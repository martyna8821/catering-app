package pl.martyna.catering.app.service.recipe.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.martyna.catering.app.entity.recipe.MealType;
import pl.martyna.catering.app.exception.ResourceNotFoundException;
import pl.martyna.catering.app.repository.recipe.IMealTypeRepository;
import pl.martyna.catering.app.service.recipe.IMealTypeService;

@Service
public class MealTypeService implements IMealTypeService {

    private IMealTypeRepository mealTypeRepository;

    @Autowired
    public MealTypeService(IMealTypeRepository  mealTypeRepository){
        this.mealTypeRepository = mealTypeRepository;
    }


    @Override
    public MealType findByName(String name) {
        return this.mealTypeRepository.findAllByName(name)
                                      .orElseThrow(ResourceNotFoundException::new);
    }
}
