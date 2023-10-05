package com.austral.triviagoservice.presentation.controller;

import com.austral.triviagoservice.business.impl.UserServiceImpl;
import com.austral.triviagoservice.persistence.domain.User;
import com.austral.triviagoservice.presentation.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PutMapping("/save-quiz/{quizId}")
    public ResponseEntity<UserDto> addQuizToSavedList(@PathVariable("quizId") Long quizId) {
        UserDto updatedUser = userService.addQuizToSavedList(quizId);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PutMapping("/remove-quiz/{quizId}")
    public ResponseEntity<UserDto> removeQuizFromSavedList(@PathVariable("quizId") Long quizId) {
        UserDto updatedUser = userService.removeQuizFromSavedList(quizId);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
}
