package com.austral.triviagoservice.presentation.dto;

import com.austral.triviagoservice.persistence.domain.Answer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerDto {
    private Long id;
    private String content;

    public AnswerDto(Answer answer) {
        this.id = answer.getId();
        this.content = answer.getContent();
    }

    public AnswerDto(Long id, String content) {
        this.id = id;
        this.content = content;
    }
    public AnswerDto() {
    }

}
