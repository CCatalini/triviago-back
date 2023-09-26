package com.austral.triviagoservice.presentation.dto;

import com.austral.triviagoservice.persistence.domain.Question;
import com.austral.triviagoservice.persistence.domain.Quiz;
import com.austral.triviagoservice.persistence.domain.Label;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class QuizCreate {

    private Long id;
    private AuthorDto author;
    private String title;
    private String description;
    private LocalDate creationDate;
    private Double rating;
    private String invitationCode;
    private Boolean isPrivate;
    private List<Question> questions;
    private List<Label> labels;




}
