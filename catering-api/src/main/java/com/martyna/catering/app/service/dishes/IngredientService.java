package com.martyna.catering.app.service.dishes;

import com.martyna.catering.app.dto.IngredientToCreate;
import com.martyna.catering.app.dto.NutritionDTO;
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
    //IIngredientNutritionRepository ingredientNutritionRepository;

    @Autowired
    public IngredientService(IIngredientRepository ingredientRepository, IMeasurementUnitService unitService,
                             INutritionService nutritionService){
        this.ingredientRepository = ingredientRepository;
        this.unitService = unitService;
        this.nutritionService = nutritionService;
        this.ingredientRepository = ingredientRepository;
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
        for(NutritionDTO nut: ingredient.getNutrition()){
            ingredientToSave.addNutrition(
                    this.nutritionService.getByName(
                            nut.getPolishName()),
                    nut.getValue()
            );
        }

        return this.ingredientRepository.save(ingredientToSave);
    }

    public void addNutritions(IngredientToCreate ingredientN, Ingredient ing){

    }

}
