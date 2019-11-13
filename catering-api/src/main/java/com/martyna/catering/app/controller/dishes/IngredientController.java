package com.martyna.catering.app.controller.dishes;

import com.martyna.catering.app.dto.IngredientToCreate;
import com.martyna.catering.app.dto.IngredientToCreateDTO;
import com.martyna.catering.app.entity.users.User;
import com.martyna.catering.app.security.dto.RegisterRequest;
import com.martyna.catering.app.service.dishes.IIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        //if(userService.existsUserByUsernameOrEmail(registerRequest.getUsername(), registerRequest.getEmail())){
          //  return ResponseEntity.unprocessableEntity().body("Username or email address alredy taken");
        //}

       // User savedUser = userService.save(registerRequest);
       // return new ResponseEntity(savedUser, HttpStatus.CREATED);
        return ResponseEntity.ok("ok");
    }
}
