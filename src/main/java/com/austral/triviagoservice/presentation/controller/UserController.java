package com.austral.triviagoservice.presentation.controller;

import com.austral.triviagoservice.business.exception.InvalidContentException;
import com.austral.triviagoservice.business.exception.NotFoundException;
import com.austral.triviagoservice.business.impl.UserServiceImpl;
import com.austral.triviagoservice.presentation.dto.QuizDto;
import com.austral.triviagoservice.presentation.dto.UserDto;
import com.austral.triviagoservice.presentation.dto.UserFieldControllerDto;
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

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserInfo(@PathVariable("id") Long userId){
        try{
            UserInfoDto dto = userService.getUserInfo(userId);
            return new ResponseEntity<>(dto,HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modifyUserInfo(@PathVariable("id") Long user_id, UserFieldControllerDto requested){
        try{
            UserInfoDto dto = userService.ModifyUserInfo(user_id, requested);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        catch (InvalidContentException | NotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
