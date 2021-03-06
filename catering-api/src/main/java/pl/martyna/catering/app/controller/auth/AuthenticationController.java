package pl.martyna.catering.app.controller.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.martyna.catering.app.entity.auth.PasswordResetToken;
import pl.martyna.catering.app.entity.auth.User;
import pl.martyna.catering.app.exception.UserNotFoundException;
import pl.martyna.catering.app.dto.auth.LoginResponse;
import pl.martyna.catering.app.dto.auth.LoginRequest;
import pl.martyna.catering.app.security.jwt.JwtProvider;
import pl.martyna.catering.app.security.service.UserPrinciple;
import pl.martyna.catering.app.service.mail.IEmailService;
import pl.martyna.catering.app.service.users.IPasswordResetTokenService;
import pl.martyna.catering.app.service.users.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/auth")
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

        return new ResponseEntity<>( new LoginResponse( jwtToken, userDetails.getUsername(),
                                           userDetails.getId().toString(), userDetails.getAuthorities()),
                                     HttpStatus.CREATED);
    }

    @PostMapping("/password-token")
    public ResponseEntity<?> sendNewPasswordToken(@Valid @RequestBody String userEmail, HttpServletRequest request){

        User user = userService.findByEmail(userEmail);

        PasswordResetToken passwordToken = passwordTokenService.createTokenForUser(user);
        emailService.sendPasswordResetToken(request.getContextPath(), request.getLocale(),
                                            passwordToken.getToken(), user);

        return new ResponseEntity<>(passwordToken, HttpStatus.CREATED);
    }

    @GetMapping("/password-token/{token}/user/id")
    public ResponseEntity<?> getUserIdFromToken(@PathVariable String token){

        UUID userId = passwordTokenService.getUserFromToken(token)
                                                .map(User::getId)
                                                .orElseThrow(UserNotFoundException::new);

        return new ResponseEntity<>(userId, HttpStatus.OK);
    }

    @DeleteMapping("/password-token/{token}")
    public ResponseEntity<?> deletePasswordToken(@PathVariable String token){
        passwordTokenService.delete(token);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
}
