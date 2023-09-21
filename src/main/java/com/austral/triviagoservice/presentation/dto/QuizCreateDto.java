package com.austral.triviagoservice.presentation.dto;


import com.austral.triviagoservice.persistence.domain.Label;
import com.austral.triviagoservice.persistence.domain.Question;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuizCreateDto {
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private boolean isPrivate;
    @NotNull
    private List<QuestionCreateDto> questions;
    @NotNull
    private List<LabelCreateDto> labels;

}
