package com.martyna.catering.app.controller.ingredient;

import com.martyna.catering.app.dto.IngredientToCreate;
import com.martyna.catering.app.service.dishes.IIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/ingredient")
public class IngredientController {

    private IIngredientService ingredientService;


    @Autowired
    public IngredientController(IIngredientService ingredientService){

        this.ingredientService = ingredientService;
    }

    @PostMapping("/list")
    public ResponseEntity<?> add(@Valid @RequestBody List<IngredientToCreate> ingredients){
        ingredients.forEach(this.ingredientService::saveIngredient);
        return ResponseEntity.ok("created");
    }
}
