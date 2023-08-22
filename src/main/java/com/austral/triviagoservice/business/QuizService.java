package com.austral.triviagoservice.business;

import com.austral.triviagoservice.business.exception.InvalidContentException;
import com.austral.triviagoservice.persistance.domain.Quiz;
import com.austral.triviagoservice.presentation.dto.QuizCreate;
import com.austral.triviagoservice.presentation.dto.QuizFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuizService {

    QuizCreate findById(long id) throws InvalidContentException;

    Page<Quiz> findAll(QuizFilter quizFIlter, Pageable pageable) throws InvalidContentException;

    QuizCreate createQuiz(Quiz quiz);
}
