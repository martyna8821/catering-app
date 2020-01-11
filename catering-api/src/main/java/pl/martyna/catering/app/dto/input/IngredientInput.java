package pl.martyna.catering.app.dto.input;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class IngredientInput {

    private String name;
    private double quantity;
    private String unit;
    private Set<String> allergens;
    private Set<String> brands;
    private Set<String> ingredientTypes;
    private Set<NutritionInput> nutrition;
}
