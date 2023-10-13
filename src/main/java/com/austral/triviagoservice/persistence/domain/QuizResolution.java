package com.austral.triviagoservice.persistence.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Builder
public class QuizResolution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long quizId;
    private int correctAnswers;
    private LocalDateTime resolutionDateTime;

    public QuizResolution(){}

    public QuizResolution(Long userId, Long quizId, int correctAnswers){
        this.userId = userId;
        this.quizId = quizId;
        this.correctAnswers = correctAnswers;
        this.resolutionDateTime = LocalDateTime.now();

    }



}
