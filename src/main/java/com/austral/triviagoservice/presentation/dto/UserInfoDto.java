package com.austral.triviagoservice.presentation.dto;

import com.austral.triviagoservice.persistence.domain.Quiz;
import com.austral.triviagoservice.persistence.domain.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class UserInfoDto {

    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    List<QuizDto> createdQuizzes;
    List<QuizDto> savedQuizzes;
    List<QuizDto> following;
    private LocalDate createdDate;

    public UserInfoDto(User user){
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setBirthDate(user.getBirthDate());
        this.setEmail(user.getEmail());

        List<QuizDto> savedQuizesDto = new ArrayList<>();
        user.getSavedQuizzes().stream().map(QuizDto::createDto).forEach(savedQuizesDto::add);
        this.setSavedQuizzes(savedQuizesDto);

        List<QuizDto> createdQuiz = new ArrayList<>();
        user.getQuizzes().stream().map(QuizDto::createDto).forEach(createdQuiz::add);
        this.setCreatedQuizzes(createdQuiz);

        this.setCreatedDate(user.getCreationDate());
    }


}
