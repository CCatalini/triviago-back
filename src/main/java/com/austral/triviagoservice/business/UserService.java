package com.austral.triviagoservice.business;

import com.austral.triviagoservice.business.exception.InvalidContentException;
import com.austral.triviagoservice.business.exception.NotFoundException;
import com.austral.triviagoservice.business.exception.UnauthorizedException;
import com.austral.triviagoservice.persistence.domain.User;
import com.austral.triviagoservice.presentation.dto.*;

import java.util.List;

public interface UserService {

    public void saveUser(User user);

    User findByEmail(String username);

    User findById(Long id) throws NotFoundException;

    UserDto addQuizToSavedList(Long quizId);

    UserDto removeQuizFromSavedList(Long quizId);

    List<QuizDto> getSavedQuizzes();

    void deleteUser(Long userId) throws UnauthorizedException;

    UserInfoDto getUserInfo(Long userId) throws NotFoundException;

    UserInfoDto modifyUserInfo(Long userId, ModifyUserInfoDto modifyUsertInfoDto) throws InvalidContentException, NotFoundException;

    UserInfoDto followUser(Long followingId) throws NotFoundException, InvalidContentException;

    UserInfoDto unfollowUser(Long followingId) throws NotFoundException, InvalidContentException;
}

