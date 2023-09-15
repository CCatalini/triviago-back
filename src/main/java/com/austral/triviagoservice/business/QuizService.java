package com.austral.triviagoservice.business;

import com.austral.triviagoservice.business.exception.InvalidContentException;
import com.austral.triviagoservice.persistence.domain.Quiz;
import com.austral.triviagoservice.presentation.dto.QuizCreate;
import com.austral.triviagoservice.presentation.dto.QuizFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuizService {

    QuizCreate findById(Long id) throws InvalidContentException;

    Page<Quiz> findAll(QuizFilter quizFIlter, Pageable pageable) throws InvalidContentException;

    QuizCreate createQuiz(Quiz quiz);

    Long deleteById(Long id) throws InvalidContentException;

    QuizCreate findByInvitationCode(String invitationCode) throws InvalidContentException;
}
