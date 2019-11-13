package com.martyna.catering.app.service.dishes;

import com.martyna.catering.app.dto.IngredientToCreate;
import com.martyna.catering.app.entity.dishes.Ingredient;
import com.martyna.catering.app.entity.dishes.MeasurementUnit;
import com.martyna.catering.app.repository.dishes.IIngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IngredientService implements IIngredientService{

    IIngredientRepository ingredientRepository;
    IMeasurementUnitService unitService;
    INutritionService nutritionService;

    @Autowired
    public IngredientService(IIngredientRepository ingredientRepository, IMeasurementUnitService unitService,
                             INutritionService nutritionService){
        this.ingredientRepository = ingredientRepository;
        this.unitService = unitService;
        this.nutritionService = nutritionService;
    }

    @Override
    public Ingredient saveIngredient(IngredientToCreate ingredient) {
        Ingredient ingredientToSave = new Ingredient();
        ingredientToSave.setId(UUID.randomUUID());
        ingredientToSave.setName(ingredient.getName());
        ingredientToSave.setQuantity(ingredient.getQuantity());
        MeasurementUnit ingredientUnit = this.unitService.getUnitByAbbreviation(ingredient.getUnit());
        ingredientToSave.setMeasurementUnit(ingredientUnit);
        ingredientToSave.setAllergens(ingredient.getAllergens());
        ingredientToSave.setBrands(ingredient.getBrands());
        ingredientToSave.setLabels(ingredient.getLabels());
        //Ingredient saved = t
        ingredient.getNutrition().forEach(nutrition -> {
                        ingredientToSave.addNutrition(
                                    this.nutritionService.getByName(
                                                    nutrition.getPolishName()),
                                    nutrition.getValue()
                        );
        });

        return this.ingredientRepository.save(ingredientToSave);
    }
}
