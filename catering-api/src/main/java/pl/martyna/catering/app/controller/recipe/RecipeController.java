package pl.martyna.catering.app.controller.recipe;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.martyna.catering.app.dto.input.RecipeInput;
import pl.martyna.catering.app.dto.resource.RecipeResource;
import pl.martyna.catering.app.entity.recipe.Recipe;
import pl.martyna.catering.app.entity.recipe.RecipeIngredient;
import pl.martyna.catering.app.entity.recipe.RecipeIngredientId;
import pl.martyna.catering.app.service.recipe.IRecipeService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/recipes")
public class RecipeController {


    IRecipeService recipeService;
    private ModelMapper modelMapper;

    @Autowired
    public RecipeController(IRecipeService recipeService, ModelMapper modelMapper){
        this.recipeService = recipeService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<?> addRecipe(@RequestBody RecipeInput recipeToCreate){
        Recipe recipe= modelMapper.map(recipeToCreate, Recipe.class);
        recipe.setId(UUID.randomUUID());
        recipe.getIngredients().forEach(ingredient -> {
            ingredient.setRecipe(recipe);
            ingredient.getId().setRecipe(recipe.getId());
        });
        Recipe saved = this.recipeService.add(recipe);
        return  ResponseEntity.ok(modelMapper.map(saved, RecipeResource.class));
    }

    @GetMapping
    public List<RecipeResource> getAllDiets(){
        List<Recipe> recipes = this.recipeService.getAl();
        return recipes.stream()
                .map(recipe -> modelMapper.map(recipe, RecipeResource.class))
                .collect(Collectors.toList());
    }


}
