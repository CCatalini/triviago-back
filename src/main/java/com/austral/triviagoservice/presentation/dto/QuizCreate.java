package com.austral.triviagoservice.presentation.dto;

import com.austral.triviagoservice.persistence.domain.Quiz;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class QuizCreate {

    private Long id;
    private Long userId;
    private String title;
    private String description;
    private LocalDate creationDate;
    private Double rating;
    private String invitationCode;

    public static QuizCreate createDTO(Quiz quiz){
        QuizCreate dto = new QuizCreate();
        dto.setTitle(quiz.getTitle());
        dto.setDescription(quiz.getDescription());
        dto.setCreationDate(quiz.getCreationDate());
        dto.setRating(quiz.getRating());
        dto.setUserId(quiz.getUserId());
        dto.setInvitationCode(quiz.getInvitationCode());
        dto.setId(quiz.getId());
        return dto;
    }
}
