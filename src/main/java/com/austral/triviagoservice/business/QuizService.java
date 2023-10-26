package com.austral.triviagoservice.business;

import com.austral.triviagoservice.business.exception.InvalidContentException;
import com.austral.triviagoservice.business.exception.NotFoundException;
import com.austral.triviagoservice.persistence.domain.Quiz;
import com.austral.triviagoservice.presentation.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuizService {

    Quiz findById(Long id) throws InvalidContentException;

    Page<QuizDto> findAll(QuizFilter quizFIlter, Pageable pageable) throws InvalidContentException;

    QuizDto create(QuizCreateDto quizCreateDto) throws InvalidContentException;

    Long deleteById(Long id) throws InvalidContentException;

    QuizDto findByInvitationCode(String invitationCode) throws InvalidContentException;
  
    void rateQuiz(Long quizId, QuizRatingDto rate) throws InvalidContentException;

    List<QuizResolutionDto> getLeaderboard(Long quizId) throws InvalidContentException, NotFoundException;
}

