package com.austral.triviagoservice.business.impl;

import com.austral.triviagoservice.business.AnswerService;
import com.austral.triviagoservice.business.exception.NotFoundException;
import com.austral.triviagoservice.persistence.domain.Answer;
import com.austral.triviagoservice.persistence.repository.AnswerRepository;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl implements AnswerService {
    private AnswerRepository answerRepository;

    public AnswerServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }
    public Answer findById(Long id) throws NotFoundException {
        return answerRepository.findById(id).orElseThrow(() -> new NotFoundException("Answer not found with id: " + id));
    }
}
