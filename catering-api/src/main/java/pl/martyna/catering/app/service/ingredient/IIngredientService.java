package pl.martyna.catering.app.service.ingredient;

import pl.martyna.catering.app.dto.input.IngredientInput;
import pl.martyna.catering.app.entity.ingredient.Ingredient;

import java.util.List;
import java.util.UUID;

public interface IIngredientService {

    Ingredient save(Ingredient ingredient);
    List<Ingredient> getAll();
    Ingredient getById(UUID id);
    Ingredient getByName(String ingredientName);
}
