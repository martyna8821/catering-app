package pl.martyna.catering.app.controller.ingredient;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.martyna.catering.app.dto.input.IngredientInput;
import pl.martyna.catering.app.dto.resource.IngredientResource;
import pl.martyna.catering.app.entity.ingredient.Ingredient;
import pl.martyna.catering.app.exception.ResourceNotFoundException;
import pl.martyna.catering.app.service.ingredient.IIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/ingredients")
public class IngredientController {

    private IIngredientService ingredientService;
    private ModelMapper modelMapper;

    @Autowired
    public IngredientController(IIngredientService ingredientService, ModelMapper modelMapper){

        this.ingredientService = ingredientService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/all")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> add(@Valid @RequestBody List<IngredientInput> ingredients){

        ingredients.forEach(this.ingredientService::saveIngredient);
        return new ResponseEntity<>("successfully added ingredients", HttpStatus.CREATED);
    }

    @PostMapping("")
    public ResponseEntity<?> addIngredient(@RequestBody IngredientInput ingredientInput){

        Ingredient savedIngredient = this.ingredientService.saveIngredient(ingredientInput);
        return new ResponseEntity<>(savedIngredient, HttpStatus.CREATED);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<IngredientResource> getAllIngredients(){

        List<Ingredient> ingredientsList = this.ingredientService.getAll();
        return ingredientsList.stream()
                .map(ingredient -> modelMapper.map(ingredient, IngredientResource.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@RequestBody UUID id){
        Ingredient ingredient = this.ingredientService.getById(id).orElseThrow(ResourceNotFoundException::new);
        return new ResponseEntity<>(modelMapper.map(ingredient, IngredientResource.class), HttpStatus.OK);
    }


    @PostMapping("/{id}")
    public ResponseEntity<?> updateDiet(@RequestBody UUID id){
        return null;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> deteleById(@RequestBody UUID id){
        return null;
    }

}