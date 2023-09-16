package com.austral.triviagoservice.presentation.dto;

import org.springframework.format.annotation.DateTimeFormat;
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate dateFrom;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate dateTo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate creationDate;
    Integer minQuestion;
    Integer maxQuestion;
    Double rating;
    Double minRating;
    Double maxRating;
}
