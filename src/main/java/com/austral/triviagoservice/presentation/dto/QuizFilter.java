package com.austral.triviagoservice.presentation.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter

public class QuizFilter {

    Long userId;
    String title;
    List<String> labels;
    LocalDate dateFrom;
    LocalDate dateTo;
    LocalDate creationDate;
    Integer minQuestion;
    Integer maxQuestion;
    Double rating;
    Double minRating;
    Double maxRating;
}
