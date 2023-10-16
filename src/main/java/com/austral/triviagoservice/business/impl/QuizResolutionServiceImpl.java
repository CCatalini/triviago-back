package com.austral.triviagoservice.business.impl;

import com.austral.triviagoservice.business.QuizResolutionService;
import com.austral.triviagoservice.persistence.domain.Answer;
import com.austral.triviagoservice.persistence.domain.QuizResolution;
import com.austral.triviagoservice.persistence.domain.User;
import com.austral.triviagoservice.persistence.repository.QuizResolutionRepository;
import com.austral.triviagoservice.presentation.dto.AnswerDto;
import com.austral.triviagoservice.presentation.dto.QuizResolutionDto;
import lombok.SneakyThrows;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class QuizResolutionServiceImpl implements QuizResolutionService {
    private AnswerServiceImpl answerService;
    private QuizResolutionRepository quizResolutionRepository;
    public QuizResolutionServiceImpl(AnswerServiceImpl answerService, QuizResolutionRepository quizResolutionRepository) {
        this.answerService = answerService;
        this.quizResolutionRepository = quizResolutionRepository;
    }

    @Override
    public QuizResolution createQuizResolution(QuizResolutionDto quizResolutionDto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long quizId = quizResolutionDto.getQuizId();
        QuizResolution quizResolution = new QuizResolution();
        quizResolution.setUserId(user.getId());
        quizResolution.setQuizId(quizId);
        quizResolution.setResolutionDateTime(LocalDateTime.now());
        quizResolution.setCorrectAnswers(0);
        quizResolutionDto.getSelectedAnswers().forEach(answerDto -> {
            if (isCorrectAnswer(answerDto) && isAnswerInQuizQuestion(answerDto, quizId)) {
                quizResolution.setCorrectAnswers(quizResolution.getCorrectAnswers() + 1);
            }
        });
        quizResolutionRepository.save(quizResolution);
        return quizResolution;
    }

    @SneakyThrows
    private boolean isCorrectAnswer(AnswerDto answerDto) {
        return answerService.findById(answerDto.getId()).isCorrect();
    }

    @SneakyThrows
    private boolean isAnswerInQuizQuestion(AnswerDto answerDto, Long quizId) {
        Answer answer = answerService.findById(answerDto.getId());
        return answer.getQuestion().getQuiz().getId() == quizId;
    }
}
