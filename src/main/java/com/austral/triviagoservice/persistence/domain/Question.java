package com.austral.triviagoservice.persistence.domain;

import com.austral.triviagoservice.presentation.dto.QuestionCreateDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String content;


    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Quiz.class)
    @JsonIgnore //json loop
    private Quiz quiz;

    public Question() {
    }

    public Question(QuestionCreateDto questionCreateDto) {
        this.content = questionCreateDto.getContent();
    }
    public Question(QuestionCreateDto questionCreateDto, Quiz quiz) {
        this.content = questionCreateDto.getContent();
        this.quiz = quiz;
    }




}
