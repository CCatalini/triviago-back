package com.austral.triviagoservice.presentation.dto;

import com.austral.triviagoservice.persistence.domain.Question;
import com.austral.triviagoservice.persistence.domain.Quiz;
import com.austral.triviagoservice.persistence.domain.Label;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

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
    private Boolean isPrivate;
    private List<Question> questions;
    private List<Label> labels;

    public static QuizCreate createDTO(Quiz quiz){
        QuizCreate dto = new QuizCreate();
        dto.setTitle(quiz.getTitle());
        dto.setDescription(quiz.getDescription());
        dto.setCreationDate(quiz.getCreationDate());
        dto.setRating(quiz.getRating());
        dto.setUserId(quiz.getUserId());
        dto.setInvitationCode(quiz.getInvitationCode());
        dto.setId(quiz.getId());
        dto.setIsPrivate(quiz.getIsPrivate());
        dto.setQuestions(quiz.getQuestions());
        dto.setLabels(quiz.getLabels());
        return dto;
    }
}
