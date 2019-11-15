package pl.martyna.catering.app.service.ingredient;

import pl.martyna.catering.app.dto.input.IngredientInput;
import pl.martyna.catering.app.dto.input.NutritionInput;
import pl.martyna.catering.app.entity.ingredient.Ingredient;

import java.lang.management.OperatingSystemMXBean;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface IIngredientService {

    Ingredient saveIngredient(IngredientInput ingredient);

    List<Ingredient> getAll();
    Optional<Ingredient> getById(UUID id);
    boolean removeById(UUID id);
}
