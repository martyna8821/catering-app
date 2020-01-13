package pl.martyna.catering.app.dto.input;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class IngredientInput {

    private String name;
    private String unit;
    private Set<String> allergens = new HashSet<>();;
    private Set<String> brands = new HashSet<>();;
    private Set<NutritionInput> nutrition;
}
