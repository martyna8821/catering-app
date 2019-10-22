package com.martyna.catering.app.controller;

import com.martyna.catering.app.dto.UserDTO;
import com.martyna.catering.app.entity.User;
import com.martyna.catering.app.exception.UserNotFoundException;
import com.martyna.catering.app.security.dto.RegisterRequest;
import com.martyna.catering.app.security.jwt.JwtAuthTokenFilter;
import com.martyna.catering.app.service.IUserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/users")
public class UserController {

    private IUserService userService;
    private ModelMapper modelMapper;

    @Autowired
    public UserController(IUserService userService, ModelMapper modelMapper){
        this.modelMapper = modelMapper;
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

    @GetMapping("/{id}")
    public User getUser(@PathVariable UUID id){

            return userService.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @GetMapping("")
    public List<UserDTO> getUsers(){

        List<User> allUsers = userService.findAll();
       return allUsers.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
               .collect(Collectors.toList());
    }

    //@TODO zrobic z query parameter pobieranie usera po username
    //  albo sprawdzajacy czy istnieje dany user

  //  @GetMapping("/{username}")
   // public ResponseEntity<?> getUserByUsername(@PathVariable String username){
    //    User userToReturn = userService.findByUsername(username).orElse(null);
     //   return new ResponseEntity<>(userToReturn, HttpStatus.OK);
   // }

    @DeleteMapping("/{email}")
    public ResponseEntity<?> deleteUserByEmail(@PathVariable String email){
        this.userService.deleteByEmail(email);
        return  ResponseEntity.ok("User with email "+email+" was successfully deleted" );
    }
}
