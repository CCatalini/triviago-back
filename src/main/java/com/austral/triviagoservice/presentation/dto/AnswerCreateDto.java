package com.austral.triviagoservice.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerCreateDto {

    @NotNull
    private String content;

    @JsonProperty("isCorrect")
    @NotNull
    private boolean isCorrect;
}
