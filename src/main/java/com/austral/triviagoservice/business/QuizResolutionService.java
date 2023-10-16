package com.austral.triviagoservice.business;

import com.austral.triviagoservice.persistence.domain.QuizResolution;
import com.austral.triviagoservice.presentation.dto.QuizResolutionDto;
import org.springframework.stereotype.Service;

@Service
public interface QuizResolutionService {

    public QuizResolution createQuizResolution(QuizResolutionDto quizResolutionDto);

}
