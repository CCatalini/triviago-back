package com.austral.triviagoservice.presentation.dto;

import com.austral.triviagoservice.persistence.domain.Question;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class QuestionDto {
    private final Long id;
    private final String content;
    private final List<AnswerDto> answers;

    public QuestionDto(Question question) {
        this.id = question.getId();
        this.content = question.getContent();
        this.answers = question.getAnswers().stream().map(AnswerDto::new).collect(Collectors.toList());
    }
}
