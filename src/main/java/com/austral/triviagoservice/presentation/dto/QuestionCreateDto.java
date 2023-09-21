package com.austral.triviagoservice.presentation.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class QuestionCreateDto {
    @NotNull
    private String content;
}
