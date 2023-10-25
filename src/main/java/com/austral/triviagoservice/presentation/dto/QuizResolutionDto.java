package com.austral.triviagoservice.presentation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class QuizResolutionDto {
    private long quizId;
    private List<AnswerDto> selectedAnswers;

    public QuizResolutionDto() {
    }

    public QuizResolutionDto(long quizId, List<AnswerDto> selectedAnswers) {
        this.quizId = quizId;
        this.selectedAnswers = selectedAnswers;
    }
}
