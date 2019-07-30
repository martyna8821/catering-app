package com.martyna.catering.app.controller;

import com.martyna.catering.app.entity.User;
import com.martyna.catering.app.security.dto.RegisterRequest;
import com.martyna.catering.app.security.jwt.JwtAuthTokenFilter;
import com.martyna.catering.app.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/users")
public class UserController {

    private IUserService userService;

    @Autowired
    public UserController(IUserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest){

        if(userService.existsUserByUsernameOrEmail(registerRequest.getUsername(), registerRequest.getEmail())){
            return ResponseEntity.unprocessableEntity().body("Username or email address alredy taken");
        }

        User savedUser = userService.save(registerRequest);
        return new ResponseEntity(savedUser, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody String newPassword, @PathVariable UUID id){

        userService.resetPassword(newPassword, id);
        return new ResponseEntity<>( HttpStatus.OK);
    }


}
