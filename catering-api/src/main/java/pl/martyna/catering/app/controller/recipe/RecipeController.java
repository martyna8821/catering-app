package pl.martyna.catering.app.controller.recipe;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.martyna.catering.app.dto.input.RecipeInput;
import pl.martyna.catering.app.dto.resource.RecipeResource;
import pl.martyna.catering.app.entity.recipe.Recipe;
import pl.martyna.catering.app.service.recipe.IRecipeService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/recipes")
public class RecipeController {


    private IRecipeService recipeService;
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
        RecipeResource savedRecipe = modelMapper.map( this.recipeService.add(recipe), RecipeResource.class);
        return  new ResponseEntity<>(savedRecipe, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllRecipes(){
        List<RecipeResource> recipesList = this.recipeService.getAl()
                                                            .stream()
                                                            .map(recipe ->
                                                                    modelMapper.map(recipe, RecipeResource.class))
                                                            .collect(Collectors.toList());

        return new ResponseEntity<>(recipesList, HttpStatus.OK);
    }
}
