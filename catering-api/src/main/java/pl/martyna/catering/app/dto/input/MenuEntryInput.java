package pl.martyna.catering.app.dto.input;

import lombok.Data;
import pl.martyna.catering.app.dto.resource.RecipeResource;
import pl.martyna.catering.app.entity.recipe.Recipe;

@Data
public class MenuEntryInput {

    private String mealType;
    private RecipeResource recipe;
    private int amount;
    private  String caloricValue;
}
