package com.austral.triviagoservice.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class QuizRatingDto {

    private Integer rating;

    public QuizRatingDto(Integer rating){
        this.rating = rating;
    }

}
