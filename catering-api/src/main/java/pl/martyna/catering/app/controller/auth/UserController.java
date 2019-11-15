package pl.martyna.catering.app.controller.auth;

import pl.martyna.catering.app.dto.resource.UserResource;
import pl.martyna.catering.app.entity.auth.User;
import pl.martyna.catering.app.exception.UserNotFoundException;
import pl.martyna.catering.app.dto.auth.RegisterRequest;
import pl.martyna.catering.app.service.users.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest){

        if(userService.existsUserByUsername(registerRequest.getUsername())){
            return ResponseEntity.unprocessableEntity().body("Username alredy taken");
        }

        if(userService.existsUserByEmail(registerRequest.getEmail())){
            return ResponseEntity.unprocessableEntity().body("Account with provided email already exists");
        }

        User savedUser = userService.save(registerRequest);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<UserResource> getAllUsers(){

        List<User> allUsers = userService.findAll();
        return allUsers.stream()
                .map(user -> modelMapper.map(user, UserResource.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username){
        User userToReturn = userService.findByUsername(username).orElse(null);
        return new ResponseEntity<>(userToReturn, HttpStatus.OK);
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody String newPassword, @PathVariable UUID id){

        userService.resetPassword(newPassword, id);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<?> deleteUserByEmail(@PathVariable String email){
        this.userService.deleteByEmail(email);
        return  ResponseEntity.ok("User with email "+email+" was successfully deleted" );
    }
}
