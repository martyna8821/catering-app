package com.martyna.catering.app.controller;

import com.martyna.catering.app.entity.User;
import com.martyna.catering.app.security.dto.LoginResponse;
import com.martyna.catering.app.security.dto.LoginRequest;
import com.martyna.catering.app.security.dto.RegisterRequest;
import com.martyna.catering.app.security.jwt.JwtProvider;
import com.martyna.catering.app.security.service.UserPrinciple;
import com.martyna.catering.app.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private JwtProvider jwtProvider;
    private IUserService userService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtProvider jwtProvider,
                                    IUserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtProvider.generateJwtToken(authentication);
        UserPrinciple userDetails = (UserPrinciple) authentication.getPrincipal();
        return ResponseEntity.ok(new LoginResponse( jwtToken, userDetails.getUsername(),
                userDetails.getId().toString(), userDetails.getAuthorities()));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest){

        if(userService.existsUserByUsernameOrEmail(registerRequest.getUsername(), registerRequest.getEmail())){
            return ResponseEntity.unprocessableEntity().body("Username or email address alredy taken");
        }

        //TODO user should be logged?
        User savedUser = userService.save(registerRequest);
        return ResponseEntity.ok(savedUser);
    }
}
