package com.austral.triviagoservice.business.impl;

import com.austral.triviagoservice.business.QuizService;
import com.austral.triviagoservice.business.UserService;
import com.austral.triviagoservice.business.exception.InvalidContentException;
import com.austral.triviagoservice.business.exception.NotFoundException;
import com.austral.triviagoservice.persistence.domain.Quiz;
import com.austral.triviagoservice.persistence.domain.User;
import com.austral.triviagoservice.persistence.repository.UserRepository;

import com.austral.triviagoservice.presentation.dto.QuizDto;
import com.austral.triviagoservice.presentation.dto.UserDto;
import com.austral.triviagoservice.presentation.dto.ModifyUserInfoDto;
import com.austral.triviagoservice.presentation.dto.UserInfoDto;
import lombok.SneakyThrows;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final QuizService quizService;
    public UserServiceImpl(UserRepository userRepository, QuizService quizService) {
        this.userRepository = userRepository;
        this.quizService = quizService;
    }

    @Override
    public void saveUser(User user) {
        user.setCreationDate(LocalDate.now());
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String username) {
        return (User) userRepository.findByEmail(username).orElse(null);
    }

    @Override
    public User findById(Long id) throws NotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User with id: " + id + " not found!"));
    }

    @Override
    @SneakyThrows
    public UserDto addQuizToSavedList (Long quizId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Quiz quiz = quizService.findById(quizId);
        if (!user.getSavedQuizzes().contains(quiz)) {
            user.getSavedQuizzes().add(quiz);
            userRepository.save(user);
        }
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .birthDate(user.getBirthDate())
                .likes(user.getLikes())
                .savedQuizzes(user.getSavedQuizzes().stream().map(QuizDto::createDto).collect(Collectors.toList()))
                .quizzes(user.getQuizzes().stream().map(QuizDto::createDto).collect(Collectors.toList()))
                .build();

    }

    @Override
    @SneakyThrows
    public UserDto removeQuizFromSavedList (Long quizId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Quiz quiz = quizService.findById(quizId);
        if (user.getSavedQuizzes().contains(quiz)) {
            user.getSavedQuizzes().remove(quiz);
            userRepository.save(user);
        }
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .birthDate(user.getBirthDate())
                .likes(user.getLikes())
                .savedQuizzes(user.getSavedQuizzes().stream().map(QuizDto::createDto).collect(Collectors.toList()))
                .quizzes(user.getQuizzes().stream().map(QuizDto::createDto).collect(Collectors.toList()))
                .build();
    }

    @Override
    public List<QuizDto> getSavedQuizzes() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getSavedQuizzes().stream().map(QuizDto::createDto).collect(Collectors.toList());
    }

    @Override
    public UserInfoDto getUserInfo(Long userId) throws NotFoundException {
        User user = this.findById(userId);
        return new UserInfoDto(user);
    }

    @Override
    public UserInfoDto modifyUserInfo(Long userId, ModifyUserInfoDto modifyUserInfoDto) throws InvalidContentException, NotFoundException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(Objects.equals(user.getId(), userId)){
            if(modifyUserInfoDto.getBirthDate() != null){
                user.setBirthDate(modifyUserInfoDto.getBirthDate());
            }
            if(modifyUserInfoDto.getLastName() != null){
                user.setLastName(modifyUserInfoDto.getLastName());
            }
            if(modifyUserInfoDto.getFirstName() != null){
                user.setFirstName(modifyUserInfoDto.getFirstName());
            }
            userRepository.save(user);
            return new UserInfoDto(user);
        }
        throw new InvalidContentException("Invalid user Id");
    }
}
