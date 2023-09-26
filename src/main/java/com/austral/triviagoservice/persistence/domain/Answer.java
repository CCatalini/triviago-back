package com.austral.triviagoservice.persistence.domain;


import com.austral.triviagoservice.presentation.dto.AnswerCreateDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private boolean isCorrect;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Question.class)
    @JsonIgnore
    private Question question;

    public Answer() {
    }

    public Answer(AnswerCreateDto answerCreateDto, Question question) {
        this.content = answerCreateDto.getContent();
        this.isCorrect = answerCreateDto.isCorrect();
        this.question = question;
    }
}
