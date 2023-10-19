package com.austral.triviagoservice.presentation.dto;

import com.austral.triviagoservice.persistence.domain.QuizResolution;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuizResolutionDto {
    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("quizId")
    private Long quizId;

    @JsonProperty("correctAnswers")
    private int correctAnswers;

    @JsonProperty("resolutionDateTime")
    private LocalDateTime resolutionDateTime;

    public QuizResolutionDto(QuizResolution quizResolution) {
        this.userId = quizResolution.getUserId();
        this.quizId = quizResolution.getQuiz().getId();
        this.correctAnswers = quizResolution.getCorrectAnswers();
        this.resolutionDateTime = quizResolution.getResolutionDateTime();
    }
}

