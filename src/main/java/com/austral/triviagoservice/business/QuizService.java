package com.austral.triviagoservice.business;

import com.austral.triviagoservice.business.exception.InvalidContentException;
import com.austral.triviagoservice.persistence.domain.Quiz;
import com.austral.triviagoservice.presentation.dto.QuizCreateDto;
import com.austral.triviagoservice.presentation.dto.QuizDto;
import com.austral.triviagoservice.presentation.dto.QuizFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuizService {

    QuizDto findById(Long id) throws InvalidContentException;

    Page<QuizDto> findAll(QuizFilter quizFIlter, Pageable pageable) throws InvalidContentException;

    QuizDto create(QuizCreateDto quizCreateDto) throws InvalidContentException;

    Long deleteById(Long id) throws InvalidContentException;

    QuizDto findByInvitationCode(String invitationCode) throws InvalidContentException;

    QuizDto toDto(Quiz quiz);
}
