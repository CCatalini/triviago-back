package com.austral.triviagoservice.business;

import com.austral.triviagoservice.business.exception.NotFoundException;
import com.austral.triviagoservice.persistence.domain.Answer;

public interface AnswerService {
    Answer findById(Long id) throws NotFoundException;
}
