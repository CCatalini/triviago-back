package com.austral.triviagoservice.presentation.controller;

import com.austral.triviagoservice.business.exception.NotFoundException;
import com.austral.triviagoservice.business.impl.UserServiceImpl;
import com.austral.triviagoservice.presentation.dto.QuizDto;
import com.austral.triviagoservice.presentation.dto.UserDto;
import com.austral.triviagoservice.presentation.dto.UserInfoDto;
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

    @GetMapping("/{user_id}")
    public ResponseEntity<?> getUserInfo(@PathVariable("user_id") Long user_id){
        try{
            UserInfoDto dto = userService.getUserInfo(user_id);
            return new ResponseEntity<>(dto,HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
