package pl.martyna.catering.app.dto.input;

import lombok.Data;
import pl.martyna.catering.app.dto.resource.IngredientResource;

@Data
public class RecipeIngredientInput {

    private IngredientResource ingredient;
    private int value;
}
