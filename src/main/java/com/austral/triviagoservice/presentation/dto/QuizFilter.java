package com.austral.triviagoservice.presentation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDate dateFrom;
    @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDate dateTo;
    @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDate creationDate;
    Integer questionQty;
    Integer minQuestion;
    Integer maxQuestion;
    Double rating;
    Double minRating;
    Double maxRating;
}
