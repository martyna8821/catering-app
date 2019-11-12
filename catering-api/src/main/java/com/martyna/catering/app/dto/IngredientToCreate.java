package com.martyna.catering.app.dto;


import java.util.List;
import java.util.Set;

public class IngredientToCreate {

    private String name;
    private int quantity;
    private String unit;
    private Set<String> allergens;
    private Set<String> brands;
    private Set<String> labels;
    private Set<String> ingredientTypes;
    private Set<NutritionDTO> nutrition;
}
