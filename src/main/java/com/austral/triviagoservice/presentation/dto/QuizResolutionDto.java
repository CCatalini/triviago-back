package com.austral.triviagoservice.presentation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class QuizResolutionDto {
    private long QuizId;
    private List<AnswerDto> selectedAnswers;
}
