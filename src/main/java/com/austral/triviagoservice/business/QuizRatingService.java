package com.austral.triviagoservice.business;

import com.austral.triviagoservice.persistence.domain.QuizRating;

public interface QuizRatingService {

    QuizRating create(QuizRating quizRating);

    void delete(QuizRating quizRating);
}
