package com.austral.triviagoservice.presentation.controller;

import com.austral.triviagoservice.business.impl.UserServiceImpl;
import com.austral.triviagoservice.persistence.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PutMapping("/{userId}/save-quiz/{quizId}")
    public ResponseEntity<User> addQuizToWishlist(@PathVariable("userId")Long userId,  @PathVariable("quizId") Long quizId) {
        User user = userService.addQuizToWishlist(userId, quizId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{userId}/remove-quiz/{quizId}")
    public ResponseEntity<User> removeQuizFromWishlist(@PathVariable("userId")Long userId,  @PathVariable("quizId") Long quizId) {
        User user = userService.removeFromWishlist(userId, quizId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
