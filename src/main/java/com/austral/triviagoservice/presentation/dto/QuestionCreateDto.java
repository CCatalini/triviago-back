package com.austral.triviagoservice.presentation.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter

public class QuestionCreateDto {
    @NotNull
    private String content;
    @NotNull
    private List<AnswerCreateDto> answers;
}
