package com.austral.triviagoservice.persistence.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
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

    public QuizResolution(Long userId, Quiz quiz, int correctAnswers){
        this.userId = userId;
        this.quiz = quiz;
        this.correctAnswers = correctAnswers;
        this.resolutionDateTime = LocalDateTime.now();

    public QuizResolution() {


    }
}
