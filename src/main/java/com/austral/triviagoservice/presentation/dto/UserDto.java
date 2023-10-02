package com.austral.triviagoservice.presentation.dto;

import com.austral.triviagoservice.persistence.domain.CommentLike;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public class UserDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private List<CommentLike> likes;
    private List<QuizDto> savedQuizzes;

}
