package com.austral.triviagoservice.presentation.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
@Setter

public class QuizFilter {

    Long userId;
    String title;
    String labels;
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
    Boolean isPrivate;

    public List<String> getLabelsList() {
        if (labels == null || labels.isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.asList(labels.split(","));
    }
}
