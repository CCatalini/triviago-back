package com.austral.triviagoservice.business.impl;

import com.austral.triviagoservice.business.QuizRatingService;
import com.austral.triviagoservice.persistence.domain.QuizRating;
import com.austral.triviagoservice.persistence.repository.QuizRatingRepository;
import com.austral.triviagoservice.presentation.dto.QuizRatingDto;
import org.springframework.stereotype.Service;

@Service
public class QuizRatingServiceImpl implements QuizRatingService {

    private final QuizRatingRepository quizRatingRepository;
    public QuizRatingServiceImpl(QuizRatingRepository quizRatingRepository){
        this.quizRatingRepository = quizRatingRepository;
    }

    @Override
    public QuizRating create(QuizRating quizRating) {
        return quizRatingRepository.save(quizRating);
    }

    @Override
    public void delete(QuizRating quizRating) {
        quizRatingRepository.delete(quizRating);
    }

    @Override
    public void updateRating(QuizRating quizRating, QuizRatingDto rate){
        quizRating.setRating(rate.getRating());
        delete(quizRating);

    }
}
