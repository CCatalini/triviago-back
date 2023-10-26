package com.austral.triviagoservice.business.impl;

import com.austral.triviagoservice.business.exception.UserAlreadyExistsException;
import com.austral.triviagoservice.persistence.domain.User;
import com.austral.triviagoservice.persistence.repository.UserRepository;
import com.austral.triviagoservice.presentation.dto.AuthenticationRequest;
import com.austral.triviagoservice.presentation.dto.AuthenticationResponse;
import com.austral.triviagoservice.presentation.dto.SignUpForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public AuthenticationResponse signUp(SignUpForm signUpForm) {
        if (userRepository.existsByEmail(signUpForm.getEmail()))
            throw new UserAlreadyExistsException("User with email " + signUpForm.getEmail() + " already exists");
//        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        User user = new User();
        user.setEmail(signUpForm.getEmail());
        user.setPassword(signUpForm.getPassword());
        user.setBirthDate(LocalDate.parse(signUpForm.getBirthDate()));
        user.setFirstName(signUpForm.getFirstName());
        user.setLastName(signUpForm.getLastName());
        AuthenticationResponse response = AuthenticationResponse.builder().token(jwtService.generateToken(user)).build();
        user.setCreationDate(LocalDate.now());
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
