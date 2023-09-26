package com.austral.triviagoservice.persistence.domain;

import com.austral.triviagoservice.presentation.dto.QuizCreateDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private String title;
    @Column
    private String description;
    @Column(nullable = false, columnDefinition = "DATETIME(0)")
    private LocalDateTime creationDate;
    @Column
    private double rating;
    @Column
    private String invitationCode;
    @Column
    private boolean isPrivate;


    @OneToMany(targetEntity = Question.class, cascade = CascadeType.ALL, mappedBy = "quiz")
    @JsonIgnore //json loop
    List<Question> questions;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "quizzes")
    @JsonIgnore
    List<Label> labels = new ArrayList<>();

    public Quiz() {
    }

    public Quiz(QuizCreateDto quizCreateDto, Long userId, List<Label> labels) {
        this.userId = userId;
        this.title = quizCreateDto.getTitle();
        this.description = quizCreateDto.getDescription();
        this.isPrivate = quizCreateDto.isPrivate();
        this.questions = quizCreateDto.getQuestions().stream().map(questionCreateDto -> new Question(questionCreateDto, this)).collect(Collectors.toList());
        this.labels = labels;
        labels.forEach(label -> label.getQuizzes().add(this));
        this.creationDate = LocalDateTime.now();
        this.rating = 0;
        if (quizCreateDto.isPrivate()) this.invitationCode = UUID.randomUUID().toString();
    }
}
