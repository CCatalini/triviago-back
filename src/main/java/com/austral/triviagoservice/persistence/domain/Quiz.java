package com.austral.triviagoservice.persistence.domain;

import com.austral.triviagoservice.presentation.dto.QuizCreateDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
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
    private String title;
    @Column
    private String description;
    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate creationDate;
    @Column
    private String invitationCode;
    @Column
    private boolean isPrivate;

    @OneToMany(mappedBy="quiz")
    @JsonIgnore
    private List<QuizRating> ratings;

    @OneToMany(targetEntity = Question.class, cascade = CascadeType.ALL, mappedBy = "quiz")
    @JsonIgnore //json loop
    @LazyCollection(LazyCollectionOption.FALSE)
    List<Question> questions;

    @ManyToOne(targetEntity = User.class)
    private User user;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "quizzes")
    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    List<Label> labels = new ArrayList<>();

    public Quiz() {
    }

    public Quiz(QuizCreateDto quizCreateDto, User user, List<Label> labels) {
        this.user = user;
        this.title = quizCreateDto.getTitle();
        this.description = quizCreateDto.getDescription();
        this.isPrivate = quizCreateDto.isPrivate();
        this.questions = quizCreateDto.getQuestions().stream().map(questionCreateDto -> new Question(questionCreateDto, this)).collect(Collectors.toList());
        this.labels = labels;
        labels.forEach(label -> label.getQuizzes().add(this));
        this.creationDate = LocalDate.now();
        this.ratings = new ArrayList<>();
        if (quizCreateDto.isPrivate()) this.invitationCode = UUID.randomUUID().toString();
    }
}
