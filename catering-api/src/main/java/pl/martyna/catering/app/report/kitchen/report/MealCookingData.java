package pl.martyna.catering.app.report.kitchen.report;

import lombok.Data;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import pl.martyna.catering.app.dto.resource.RecipeIngredientResource;
import pl.martyna.catering.app.dto.resource.RecipeStepResource;
import pl.martyna.catering.app.entity.recipe.Recipe;
import pl.martyna.catering.app.entity.recipe.RecipeIngredient;
import pl.martyna.catering.app.entity.recipe.RecipeStep;

import java.util.Set;

@Getter
public class MealCookingData {

    private int weight = 0;
    private int portionsNumber = 0;
    private KitchenRecipe kitchenRecipe = new KitchenRecipe();

    public void addWeight(int weight){
        this.weight += weight;
    }

    public void addPortionsNumber(int portionsNumber){
        this.portionsNumber += portionsNumber;
    }

    public void setKitchenRecipeSteps(Set<RecipeStep> recipeSteps, ModelMapper modelMapper){
        recipeSteps.forEach( step ->{
            kitchenRecipe.getRecipeSteps().add(modelMapper.map(step, RecipeStepResource.class));
        });
    }

    public void setKitchenRecipeIngredients(Set<RecipeIngredient> ingredients, ModelMapper modelMapper){
        ingredients.forEach( ingredient ->{
            kitchenRecipe.getIngredients().add(modelMapper.map(ingredient, RecipeIngredientResource.class));
        });
    }

    public void calculateIngredientsWeight(int baseRecipeWeight){

        this.kitchenRecipe.getIngredients().forEach(ingredient ->{
            int calculatedWeight = Math.round((ingredient.getValue() * this.weight)/baseRecipeWeight);
            ingredient.setValue(calculatedWeight);
        });
    }

}
