package pl.martyna.catering.app.controller.user;

import pl.martyna.catering.app.dto.input.UserUpdateInput;
import pl.martyna.catering.app.dto.resource.UserResource;
import pl.martyna.catering.app.entity.auth.User;
import pl.martyna.catering.app.exception.ResourceNotFoundException;
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

    @PostMapping
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

    @GetMapping
    public ResponseEntity<?> getAllUsers(){

        List<UserResource> allUsersList = userService.findAll()
                                                        .stream()
                                                        .map(user ->
                                                                modelMapper.map(user, UserResource.class))
                                                        .collect(Collectors.toList());

        return new ResponseEntity<>(allUsersList, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username){
        User userToReturn = userService.findByUsername(username);
        return new ResponseEntity<>(userToReturn, HttpStatus.OK);
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody String newPassword, @PathVariable UUID id){

        userService.resetPassword(newPassword, id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser( @RequestBody UserUpdateInput userToUpdate,
                            @PathVariable("id") UUID id){

       User user = this.userService.findById(id).orElseThrow(ResourceNotFoundException::new);
       user.setFirstName(userToUpdate.getFirstName());
       user.setLastName(userToUpdate.getLastName());
       user.setEmail(userToUpdate.getEmail());
       user.setPhoneNumber(userToUpdate.getPhoneNumber());
       user.setAddress(userToUpdate.getAddress());

        return  new ResponseEntity<>( this.userService.update(user), HttpStatus.OK);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<?> deleteUserByEmail(@PathVariable String email){
        this.userService.deleteByEmail(email);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}