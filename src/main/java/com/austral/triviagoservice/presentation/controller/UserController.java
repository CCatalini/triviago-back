package com.austral.triviagoservice.presentation.controller;

import com.austral.triviagoservice.business.exception.UnauthorizedException;
import com.austral.triviagoservice.business.exception.InvalidContentException;
import com.austral.triviagoservice.business.exception.NotFoundException;
import com.austral.triviagoservice.business.impl.UserServiceImpl;
import com.austral.triviagoservice.presentation.dto.QuizDto;
import com.austral.triviagoservice.presentation.dto.UserDto;
import com.austral.triviagoservice.presentation.dto.ModifyUserInfoDto;
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

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Long userId) {
        try {
            userService.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UnauthorizedException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
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
    public ResponseEntity<?> modifyUserInfo(@PathVariable("id") Long userId, @RequestBody ModifyUserInfoDto modifyUserInfoDto){
        try{
            UserInfoDto dto = userService.modifyUserInfo(userId, modifyUserInfoDto);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        catch (NotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (InvalidContentException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/follow/{following_id}")
    public ResponseEntity<?> followUser(@PathVariable("following_id") Long followingId){
        try{
            UserInfoDto dto = userService.followUser(followingId);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        catch (NotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/unfollow/{following_id}")
    public ResponseEntity<?> unfollowUser(@PathVariable("following_id") Long followingId){
        try{
            UserInfoDto dto = userService.unfollowUser(followingId);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        catch (NotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
