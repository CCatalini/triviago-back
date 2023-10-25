package com.austral.triviagoservice.business;

import com.austral.triviagoservice.business.exception.InvalidContentException;
import com.austral.triviagoservice.business.exception.NotFoundException;
import com.austral.triviagoservice.persistence.domain.QuizResolution;
import com.austral.triviagoservice.presentation.dto.QuizResolutionDto;

public interface QuizResolutionService {

    QuizResolution createQuizResolution(QuizResolutionDto quizResolutionDto) throws InvalidContentException, NotFoundException;

}
