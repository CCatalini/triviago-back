package com.austral.triviagoservice.presentation.controller;

import com.austral.triviagoservice.business.exception.InvalidContentException;
import com.austral.triviagoservice.business.impl.UserServiceImpl;
import com.austral.triviagoservice.presentation.dto.QuizDto;
import com.austral.triviagoservice.presentation.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @GetMapping("/saved-quizzes")
    public ResponseEntity<List<QuizDto>> getSavedQuizzes(){
        return new ResponseEntity<>(userService.getSavedQuizzes(), HttpStatus.OK);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") Long userId) throws InvalidContentException{
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
