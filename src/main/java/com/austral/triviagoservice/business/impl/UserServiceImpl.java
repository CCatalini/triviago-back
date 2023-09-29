package com.austral.triviagoservice.business.impl;

import com.austral.triviagoservice.business.UserService;
import com.austral.triviagoservice.business.exception.NotFoundException;
import com.austral.triviagoservice.persistence.domain.Quiz;
import com.austral.triviagoservice.persistence.domain.User;
import com.austral.triviagoservice.persistence.repository.QuizRepository;
import com.austral.triviagoservice.persistence.repository.UserRepository;
import com.austral.triviagoservice.presentation.dto.AuthorDto;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final QuizRepository quizRepository;
    public UserServiceImpl(UserRepository userRepository, QuizRepository quizRepository) {
        this.userRepository = userRepository;
        this.quizRepository = quizRepository;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String username) {
        return (User) userRepository.findByEmail(username).orElse(null);
    }

    @Override
    public AuthorDto findById(Long id) throws NotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User with id: " + id + " not found!"));
        return new AuthorDto(user);
    }

    @SneakyThrows
    public User addQuizToWishlist (Long userId, Long quizId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User with id: " + userId + " not found!"));
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new NotFoundException("Quiz with id: " + quizId + " not found!"));
        if (!user.getSavedQuizzes().contains(quiz)) {
            user.getSavedQuizzes().add(quiz);
            userRepository.save(user);
        }
        return user;
    }

    @SneakyThrows
    public User removeFromWishlist (Long userId, Long quizId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User with id: " + userId + " not found!"));
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new NotFoundException("Quiz with id: " + quizId + " not found!"));
        if (user.getSavedQuizzes().contains(quiz)) {
            user.getSavedQuizzes().remove(quiz);
            userRepository.save(user);
        }
        return user;
    }

}
