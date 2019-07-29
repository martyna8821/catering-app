package com.martyna.catering.app.controller;

import com.martyna.catering.app.entity.PasswordResetToken;
import com.martyna.catering.app.entity.User;
import com.martyna.catering.app.exception.ResourceNotFoundException;
import com.martyna.catering.app.exception.UserNotFoundException;
import com.martyna.catering.app.exception.UserUnauthorized;
import com.martyna.catering.app.security.dto.LoginResponse;
import com.martyna.catering.app.security.dto.LoginRequest;
import com.martyna.catering.app.security.dto.RegisterRequest;
import com.martyna.catering.app.security.jwt.JwtProvider;
import com.martyna.catering.app.security.service.UserPrinciple;
import com.martyna.catering.app.service.IEmailService;
import com.martyna.catering.app.service.IPasswordResetTokenService;
import com.martyna.catering.app.service.IUserService;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private JwtProvider jwtProvider;
    private IUserService userService;
    private IEmailService emailService;
    private IPasswordResetTokenService passwordTokenService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtProvider jwtProvider,
                                    IUserService userService, IEmailService emailService,
                                    IPasswordResetTokenService passwordTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.userService = userService;
        this.emailService = emailService;
        this.passwordTokenService  = passwordTokenService;
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

    @PostMapping("/password-token")
    public ResponseEntity<?> createResetPasswordToken(@Valid @RequestBody String email,
                                              HttpServletRequest request){

        User user = userService.findByEmail(email)
                                    .orElseThrow(UserNotFoundException::new);


        PasswordResetToken passwordToken = passwordTokenService.createTokenForUser(user);
        emailService.sendPasswordResetToken(request.getContextPath(), request.getLocale(),
                passwordToken.getToken(), user);

        return new ResponseEntity<>(passwordToken, HttpStatus.CREATED);
    }

    @DeleteMapping("/password-token/{token}")
    public ResponseEntity<?> deletePasswordToken(@PathVariable String token){

        passwordTokenService.delete(token);
        return new ResponseEntity<>("Reset password token successfully deleted", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/password-token/{token}/user")
    public ResponseEntity<?> getUserFromToken(@PathVariable String passwordToken){

        User userFromToken = passwordTokenService.getUserFromToken(passwordToken)
                .orElseThrow(ResourceNotFoundException::new);

        return new ResponseEntity<>(userFromToken, HttpStatus.OK);
    }
}
