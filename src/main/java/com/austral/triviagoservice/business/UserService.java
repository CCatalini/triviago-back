package com.austral.triviagoservice.business;

import com.austral.triviagoservice.business.exception.NotFoundException;
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

    UserInfoDto getUserInfo(Long userId) throws NotFoundException;
}
