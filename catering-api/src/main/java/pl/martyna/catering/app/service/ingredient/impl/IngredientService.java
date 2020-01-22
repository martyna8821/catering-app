package pl.martyna.catering.app.service.ingredient.impl;

import pl.martyna.catering.app.dto.input.IngredientInput;
import pl.martyna.catering.app.entity.ingredient.Ingredient;
import pl.martyna.catering.app.entity.ingredient.MeasurementUnit;
import pl.martyna.catering.app.exception.ResourceNotFoundException;
import pl.martyna.catering.app.repository.ingredient.IIngredientRepository;
import pl.martyna.catering.app.service.ingredient.IIngredientService;
import pl.martyna.catering.app.service.ingredient.IMeasurementUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class IngredientService implements IIngredientService {

    private IIngredientRepository ingredientRepository;

    @Autowired
    public IngredientService(IIngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        return this.ingredientRepository.save(ingredient);
    }

    @Override
    public List<Ingredient> getAll() {
        return this.ingredientRepository.findAll();
    }

    @Override
    public Ingredient getById(UUID id) {
        return this.ingredientRepository.findById(id)
                                        .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Ingredient getByName(String ingredientName) {
        return this.ingredientRepository.findByName(ingredientName)
                                        .orElseThrow(ResourceNotFoundException::new);
    }
}
