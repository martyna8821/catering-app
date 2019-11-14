package com.martyna.catering.app.controller.dishes;

import com.martyna.catering.app.dto.IngredientToCreate;
import com.martyna.catering.app.dto.IngredientToCreateDTO;
import com.martyna.catering.app.dto.NutritionDTO;
import com.martyna.catering.app.entity.dishes.Ingredient;
import com.martyna.catering.app.service.dishes.IIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/ingredient")
public class IngredientController {

    private IIngredientService ingredientService;
    //private IIngredientNutritionService ingredientNutritionService;

    @Autowired
    public IngredientController(IIngredientService ingredientService){
                                //IIngredientNutritionService ingredientNutritionService){
        this.ingredientService = ingredientService;
        //this.ingredientNutritionService = ingredientNutritionService;
    }

    @PostMapping("/list")
    public ResponseEntity<?> add(@Valid @RequestBody List<IngredientToCreate> ingredients){

        ingredients.forEach(  i -> {
           Ingredient ing = this.ingredientService.saveIngredient(i);
           System.out.println(ing.getName());
          // this.ingredientNutritionService.addIngredientNutrition(i.getNutrition(), ing);
            //this.ingredientService.addNutritions(i, ing);
        });

     //   IngredientToCreate ingredientToCreate = new IngredientToCreate();
     //   ingredientToCreate.setAllergens(Set.of("mleko", "jajka"));
     //   ingredientToCreate.setBrands(Set.of("brand1"));
     //   ingredientToCreate.setIngredientTypes(Set.of());
     //   ingredientToCreate.setLabels(Set.of());
     //   ingredientToCreate.setUnit("g");
     //   ingredientToCreate.setName("product name");
     //   ingredientToCreate.setQuantity(1);
     //   ingredientToCreate.setNutrition(Set.of(new NutritionDTO("Sól", "Sól",2.0, "g")));
     //  Ingredient ing = this.ingredientService.saveIngredient(ingredientToCreate);
     //  System.out.println(ing.getName());
       //if(userService.existsUserByUsernameOrEmail(registerRequest.getUsername(), registerRequest.getEmail())){
          //  return ResponseEntity.unprocessableEntity().body("Username or email address alredy taken");
        //}

       // User savedUser = userService.save(registerRequest);
       // return new ResponseEntity(savedUser, HttpStatus.CREATED);
        return ResponseEntity.ok("ok");
    }
}
