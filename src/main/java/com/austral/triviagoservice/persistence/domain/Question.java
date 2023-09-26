package com.austral.triviagoservice.persistence.domain;

import com.austral.triviagoservice.presentation.dto.QuestionCreateDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;


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

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Answer.class, mappedBy = "question")
    @JsonIgnore
    private List<Answer> answers;

    public Question() {
    }

    public Question(QuestionCreateDto questionCreateDto, Quiz quiz) {
        this.content = questionCreateDto.getContent();
        this.answers = questionCreateDto.getAnswers().stream().map(answerCreateDto -> new Answer(answerCreateDto, this)).collect(Collectors.toList());
        this.quiz = quiz;
    }




}
