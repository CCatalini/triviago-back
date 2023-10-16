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
    List<QuizDto> savedQuizes;
    List<QuizDto> following;
    private LocalDate createdDate;

    public static UserInfoDto dto(User user){
        UserInfoDto dto = new UserInfoDto();
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setBirthDate(user.getBirthDate());
        dto.setEmail(user.getEmail());

        List<QuizDto> savedQuizesDto = new ArrayList<>();
        user.getSavedQuizzes().stream().map(QuizDto::createDto).forEach(savedQuizesDto::add);
        dto.setSavedQuizes(savedQuizesDto);

        List<QuizDto> createdQuiz = new ArrayList<>();
        user.getQuizzes().stream().map(QuizDto::createDto).forEach(createdQuiz::add);
        dto.setCreatedQuizzes(createdQuiz);

        dto.setCreatedDate(user.getCreationDate());
        return dto;
    }


}
