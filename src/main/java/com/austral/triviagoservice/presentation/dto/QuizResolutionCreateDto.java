package com.austral.triviagoservice.presentation.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuizResolutionCreateDto {
    private long quizId;
   private List<ResolvedQuestionDto> resolvedQuestions;
    public QuizResolutionCreateDto() {
    }

    public QuizResolutionCreateDto(long quizId, List<ResolvedQuestionDto> resolvedQuestions){
        this.quizId = quizId;
        this.resolvedQuestions = resolvedQuestions;
    }
}
