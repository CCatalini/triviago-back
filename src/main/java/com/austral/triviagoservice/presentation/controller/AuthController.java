package com.austral.triviagoservice.presentation.controller;

import com.austral.triviagoservice.business.impl.AuthService;
import com.austral.triviagoservice.persistence.domain.User;
import com.austral.triviagoservice.presentation.dto.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> signUpUser(@RequestBody User user){
        return ResponseEntity.ok(authService.signUp(user));
    }


}
