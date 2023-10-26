package com.austral.triviagoservice.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ResolvedQuestionDto {
    @NotNull
    @JsonProperty("questionId")
    private Long questionId;
    @NotNull
    @JsonProperty("selectedAnswersIds")
    private List<Long> selectedAnswersIds;
}
