package pl.martyna.catering.app.service.ingredient.impl;

import pl.martyna.catering.app.dto.input.IngredientInput;
import pl.martyna.catering.app.dto.input.NutritionInput;
import pl.martyna.catering.app.entity.ingredient.Ingredient;
import pl.martyna.catering.app.entity.ingredient.MeasurementUnit;
import pl.martyna.catering.app.repository.ingredient.IIngredientRepository;
import pl.martyna.catering.app.service.ingredient.IIngredientService;
import pl.martyna.catering.app.service.ingredient.IMeasurementUnitService;
import pl.martyna.catering.app.service.ingredient.INutritionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class IngredientService implements IIngredientService {

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
    public Ingredient saveIngredient(IngredientInput ingredient) {
        Ingredient ingredientToSave = new Ingredient();
        ingredientToSave.setId(UUID.randomUUID());
        ingredientToSave.setName(ingredient.getName());
        ingredientToSave.setQuantity(ingredient.getQuantity());
        MeasurementUnit ingredientUnit = this.unitService.getUnitByAbbreviation(ingredient.getUnit());
        ingredientToSave.setMeasurementUnit(ingredientUnit);
        ingredientToSave.setAllergens(ingredient.getAllergens());
        ingredientToSave.setBrands(ingredient.getBrands());
        ingredientToSave.setLabels(ingredient.getLabels());
        for(NutritionInput nut: ingredient.getNutrition()){
            ingredientToSave.addNutrition(
                    this.nutritionService.getByName(
                            nut.getPolishName()),
                    nut.getValue()
            );
        }

        return this.ingredientRepository.save(ingredientToSave);
    }

    @Override
    public List<Ingredient> getAll() {
        return this.ingredientRepository.findAll();
    }

    @Override
    public Optional<Ingredient> getById(UUID id) {
        return Optional.empty();
    }

    @Override
    public boolean removeById(UUID id) {
        return false;
    }
}
