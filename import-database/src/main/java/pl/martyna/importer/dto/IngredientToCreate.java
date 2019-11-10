package pl.martyna.importer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IngredientToCreate {

    private String name;
    private int quantity;
    private String unit;
    private Set<String> brands;
    private Set<String> ingredientTypes = new HashSet<>();
    private Set<String> allergens;
    private Map<String, Integer> nutrition;

    public void addIngredientType(String ingredientType){
        ingredientTypes.add(ingredientType);
    }
}
