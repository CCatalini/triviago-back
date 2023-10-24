package com.austral.triviagoservice.persistence.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
public class QuizResolution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;

    @ManyToOne(targetEntity = Quiz.class)
    private Quiz quiz;
    private int correctAnswers;
    private LocalDateTime resolutionDateTime;

    public QuizResolution() {}
}
