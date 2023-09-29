package com.austral.triviagoservice.presentation.controller;

import com.austral.triviagoservice.business.impl.UserServiceImpl;
import com.austral.triviagoservice.persistence.domain.User;
import org.springframework.http.ResponseEntity;
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

    @PutMapping("/user/{userId}/save-quiz/{quizId}")
    public ResponseEntity<?> addQuizToWishlist(Long userId, Long quizId) {
        try {
            User user = userService.addQuizToWishlist(userId, quizId);
            return ResponseEntity.ok(user);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/user/{userId}/remove-quiz/{quizId}")
    public ResponseEntity<?> removeQuizFromWishlist(Long userId, Long quizId) {
        try {
            User user = userService.removeFromWishlist(userId, quizId);
            return ResponseEntity.ok(user);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
