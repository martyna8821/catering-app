package pl.martyna.catering.app.dto.resource;

import lombok.Data;
import pl.martyna.catering.app.entity.recipe.MealType;

import java.util.UUID;

@Data
public class MenuEntryResource {

    private UUID menuEntryId;
    private MealType mealType;
    private RecipeResource recipe;
    private int amount;
    private String caloricValue;
}
