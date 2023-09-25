package com.austral.triviagoservice.persistence.domain;

import com.austral.triviagoservice.presentation.dto.QuestionCreateDto;
import com.austral.triviagoservice.presentation.dto.QuizCreateDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;
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
    @Column(nullable = false)
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

    @ManyToMany(mappedBy = "quizzes")
    List<Label> labels;
  
    public Quiz(){}
    public Quiz(QuizCreateDto quizCreateDto, Long userId){
        this.userId = userId;
        this.title = quizCreateDto.getTitle();
        this.description = quizCreateDto.getDescription();
        this.isPrivate = quizCreateDto.isPrivate();
        this.questions = quizCreateDto.getQuestions().stream().map(questionCreateDto -> new Question(questionCreateDto, this)).collect(Collectors.toList());
        this.labels = quizCreateDto.getLabels().stream()
                .map(labelCreateDto -> new Label(labelCreateDto, this)).collect(Collectors.toList());
        this.creationDate = LocalDateTime.now();
        this.rating = 0;
        this.invitationCode = UUID.randomUUID().toString();
    }
}
