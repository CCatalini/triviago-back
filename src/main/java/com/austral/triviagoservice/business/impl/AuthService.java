package com.austral.triviagoservice.business.impl;

import com.austral.triviagoservice.persistence.domain.AuthenticationRequest;
import com.austral.triviagoservice.persistence.domain.User;
import com.austral.triviagoservice.persistence.repository.UserRepository;
import com.austral.triviagoservice.presentation.dto.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public AuthenticationResponse signUp(User user) {

//        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        AuthenticationResponse response = AuthenticationResponse.builder().token(jwtService.generateToken(user)).build();

        userRepository.save(user);
        return response;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        User user = (User) userRepository.findByEmail( request.getUsername() ).orElse( null );

        if (user == null) return null;

        if (request.getPassword().equals(user.getPassword()) && request.getUsername().equals(user.getEmail()) ){

            var jwtToken = jwtService.generateToken(user);

            AuthenticationResponse response = AuthenticationResponse.builder().token(jwtToken).build();;

            return response;
        }

        return null;
    }
}
