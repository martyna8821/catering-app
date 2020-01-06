package pl.martyna.catering.app.dto.input;

import lombok.Data;
import pl.martyna.catering.app.dto.resource.RecipeResource;

@Data
public class MenuEntryInput {

    private String mealType;
    private RecipeResource recipe;
    private int amount;
}
