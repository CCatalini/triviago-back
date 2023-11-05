package com.austral.triviagoservice.business;

import com.austral.triviagoservice.business.exception.InvalidContentException;
import com.austral.triviagoservice.business.exception.NotFoundException;
import com.austral.triviagoservice.presentation.dto.QuizResolutionCreateDto;
import com.austral.triviagoservice.presentation.dto.QuizResolutionDto;

public interface QuizResolutionService {

    QuizResolutionDto createQuizResolution(QuizResolutionCreateDto quizResolutionDto) throws InvalidContentException, NotFoundException;

}
