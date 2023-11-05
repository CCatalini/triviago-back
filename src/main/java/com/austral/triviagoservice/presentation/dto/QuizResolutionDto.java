package com.austral.triviagoservice.presentation.dto;
import com.austral.triviagoservice.persistence.domain.QuizResolution;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuizResolutionDto {
    @JsonProperty("userMail")
    private String email;

    @JsonProperty("quizId")
    private Long quizId;

    @JsonProperty("correctAnswers")
    private int correctAnswers;

    @JsonProperty("resolutionDateTime")
    private LocalDateTime resolutionDateTime;

    public QuizResolutionDto(QuizResolution quizResolution) {
        this.email = quizResolution.getUser().getEmail();
        this.quizId = quizResolution.getQuiz().getId();
        this.correctAnswers = quizResolution.getCorrectAnswers();
        this.resolutionDateTime = quizResolution.getResolutionDateTime();
    }
}

