package pl.martyna.catering.app.controller.ingredient;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.martyna.catering.app.dto.input.IngredientInput;
import pl.martyna.catering.app.dto.resource.IngredientResource;
import pl.martyna.catering.app.entity.ingredient.Ingredient;
import pl.martyna.catering.app.service.ingredient.IIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import pl.martyna.catering.app.service.ingredient.IMeasurementUnitService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/ingredients")
public class IngredientController {

    private IIngredientService ingredientService;
    private IMeasurementUnitService measurementUnitService;
    private ModelMapper modelMapper;

    @Autowired
    public IngredientController(IIngredientService ingredientService,
                                IMeasurementUnitService measurementUnitService,
                                ModelMapper modelMapper){

        this.ingredientService = ingredientService;
        this.measurementUnitService = measurementUnitService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/list")
    public ResponseEntity<?> add(@Valid @RequestBody List<IngredientInput> ingredients){

        ingredients.forEach(ingredient -> {
            Ingredient mappedIngredient =   this.modelMapper.map(ingredient, Ingredient.class);
            mappedIngredient.setMeasurementUnit(this.measurementUnitService.getUnitByAbbreviation("g"));
            ingredient.getNutrition().forEach( nutritionInput -> {
                if(nutritionInput.getPolishName().equals("Kalorie")){
                    if(nutritionInput.getValue() > 0.00){
                        mappedIngredient.setCaloricValue( String.valueOf(nutritionInput.getValue()));
                        this.ingredientService.save(mappedIngredient);
                    }
                }
            });
        });

        return new ResponseEntity<>("successfully added ingredients", HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<?> addIngredient(@RequestBody IngredientInput ingredientInput){

        Ingredient savedIngredient = this.ingredientService.save(
                this.modelMapper.map(ingredientInput, Ingredient.class));

        return new ResponseEntity<>(savedIngredient, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllIngredients(){

        List<IngredientResource> ingredientsList = this.ingredientService.getAll()
                                                                            .stream()
                                                                            .map(ingredient ->
                                                                                    modelMapper.map(ingredient, IngredientResource.class))
                                                                            .collect(Collectors.toList());

        return new ResponseEntity<>(ingredientsList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id){
        Ingredient ingredient = this.ingredientService.getById(id);
        return new ResponseEntity<>(modelMapper.map(ingredient, IngredientResource.class), HttpStatus.OK);
    }

}
