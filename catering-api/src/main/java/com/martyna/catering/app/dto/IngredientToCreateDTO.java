package com.martyna.catering.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class IngredientToCreateDTO {

    private String name;
    private int quantity;
    private String unit;
    private Set<String> brands;
    private Set<String> ingredientTypes;
    private Set<String> allergens;
    private Map<String, Integer> nutrition;
 }
