package com.martyna.catering.app.controller;

import com.martyna.catering.app.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.SSLSession;
import javax.servlet.http.HttpServletRequest;

import java.security.Principal;
import java.util.Base64;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 4800, allowCredentials = "false")
@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @RequestMapping("/login")
    public boolean login(@RequestBody User user) {
        return   user.getUsername().equals("user") && user.getPassword().equals("password");
    }

    @RequestMapping("/user")
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization")
                .substring("Basic".length()).trim();
        return () ->  new String(Base64.getDecoder()
                .decode(authToken)).split(":")[0];
    }


}
