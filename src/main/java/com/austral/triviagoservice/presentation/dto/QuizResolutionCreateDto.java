package com.austral.triviagoservice.presentation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class QuizResolutionCreateDto {
    private long quizId;
    private List<AnswerDto> selectedAnswers;

    public QuizResolutionCreateDto() {
    }

    public QuizResolutionCreateDto(long quizId, List<AnswerDto> selectedAnswers) {
        this.quizId = quizId;
        this.selectedAnswers = selectedAnswers;
    }
}
