package com.austral.triviagoservice.presentation.dto;

import com.austral.triviagoservice.persistence.domain.Quiz;
import com.austral.triviagoservice.persistence.domain.Label;
import com.austral.triviagoservice.persistence.domain.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class QuizDto {

    private Long id;
    private AuthorDto author;
    private String title;
    private String description;
    private LocalDate creationDate;
    private Double rating;
    private String invitationCode;
    private boolean isPrivate;
    private List<QuestionDto> questions;
    private List<String > labels;

    public QuizDto() {

    }

    public static QuizDto createDto(Quiz quiz, User author){
        QuizDto dto = new QuizDto();
        dto.setTitle(quiz.getTitle());
        dto.setDescription(quiz.getDescription());
        dto.setCreationDate(quiz.getCreationDate());
        dto.setRating(quiz.getRating());
        dto.setAuthor(new AuthorDto(author));
        dto.setInvitationCode(quiz.getInvitationCode());
        dto.setId(quiz.getId());
        dto.setPrivate(quiz.isPrivate());
        dto.setQuestions(quiz.getQuestions().stream().map(QuestionDto::new).collect(Collectors.toList()));
        dto.setLabels(quiz.getLabels().stream().map(Label::getValue).collect(Collectors.toList()));
        return dto;
    }

}
