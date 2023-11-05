package com.austral.triviagoservice.business;

import com.austral.triviagoservice.persistence.domain.QuizRating;
import com.austral.triviagoservice.presentation.dto.QuizRatingDto;

public interface QuizRatingService {

    QuizRating create(QuizRating quizRating);

    void delete(QuizRating quizRating);

    void updateRating(QuizRating quizRating, QuizRatingDto rate);
}
