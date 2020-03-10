package pl.martyna.catering.app.report.daily.kitchenreport.utils;

import lombok.Getter;
import org.modelmapper.ModelMapper;
import pl.martyna.catering.app.dto.resource.RecipeIngredientResource;
import pl.martyna.catering.app.dto.resource.RecipeStepResource;
import pl.martyna.catering.app.entity.recipe.RecipeIngredient;
import pl.martyna.catering.app.entity.recipe.RecipeStep;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Getter
public class MealCookingData {

    private int weight = 0;
    private Map<Integer, Integer> portionsWeightNumberMap = new HashMap<>();
    private KitchenReportRecipe kitchenReportRecipe = new KitchenReportRecipe();

    public void addWeight(int weight){

        this.weight += weight;
    }

    public void addPortions(int portionsNumber, int weight){

        if(!portionsWeightNumberMap.containsKey(weight)) {
            this.portionsWeightNumberMap.put(weight, portionsNumber);
        }
        else {
            int currentPortionsNumber = this.portionsWeightNumberMap.get(weight);
            this.portionsWeightNumberMap.put(weight, currentPortionsNumber+portionsNumber);
        }
    }

    public void setKitchenRecipeName(String kitchenRecipeName){

        this.kitchenReportRecipe.setName(kitchenRecipeName);
    }

    public void setKitchenRecipeSteps(Set<RecipeStep> recipeSteps, ModelMapper modelMapper){

        recipeSteps.forEach( step ->{
            kitchenReportRecipe.getRecipeSteps().add(
                    modelMapper.map(step, RecipeStepResource.class));
        });
    }

    public void setKitchenRecipeIngredients(Set<RecipeIngredient> ingredients, ModelMapper modelMapper){

        ingredients.forEach( ingredient -> {
            kitchenReportRecipe.getIngredients().add(
                    modelMapper.map(ingredient, RecipeIngredientResource.class));
        });
    }

    public void calculateIngredientsWeight(int baseRecipeWeight){

        this.kitchenReportRecipe.getIngredients()
            .forEach(ingredient ->{
                int calculatedWeight = Math.round((ingredient.getValue() * this.weight)/baseRecipeWeight);
                ingredient.setValue(calculatedWeight);
        });
    }

}
