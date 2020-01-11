package pl.martyna.catering.app.dto.input;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class RecipeInput {
    private String name;
    private List<String> mealTypes = new ArrayList<>();
    private Set<RecipeIngredientInput> ingredients = new HashSet<>();
    private Set<RecipeStepInput> recipeSteps = new HashSet<>();
    private int mealWeight;
}
