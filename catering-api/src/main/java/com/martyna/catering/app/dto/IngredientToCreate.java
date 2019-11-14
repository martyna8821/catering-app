package com.martyna.catering.app.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class IngredientToCreate {

    private String name;
    private double quantity;
    private String unit;
    private Set<String> allergens;
    private Set<String> brands;
    private Set<String> labels;
    private Set<String> ingredientTypes;
    private Set<NutritionDTO> nutrition;
}
