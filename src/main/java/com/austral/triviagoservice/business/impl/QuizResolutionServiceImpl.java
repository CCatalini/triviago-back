package com.austral.triviagoservice.business.impl;

import com.austral.triviagoservice.business.AnswerService;
import com.austral.triviagoservice.business.QuizResolutionService;
import com.austral.triviagoservice.business.QuizService;
import com.austral.triviagoservice.business.exception.InvalidContentException;
import com.austral.triviagoservice.business.exception.NotFoundException;
import com.austral.triviagoservice.persistence.domain.Answer;
import com.austral.triviagoservice.persistence.domain.Quiz;
import com.austral.triviagoservice.persistence.domain.QuizResolution;
import com.austral.triviagoservice.persistence.domain.User;
import com.austral.triviagoservice.persistence.repository.AnswerRepository;
import com.austral.triviagoservice.persistence.repository.QuestionRepository;
import com.austral.triviagoservice.persistence.repository.QuizResolutionRepository;
import com.austral.triviagoservice.presentation.dto.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuizResolutionServiceImpl implements QuizResolutionService {
    private final AnswerService answerService;
    private final QuizResolutionRepository quizResolutionRepository;
    private final QuizService quizService;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public QuizResolutionServiceImpl(AnswerService answerService, QuizResolutionRepository quizResolutionRepository, QuizService quizService,
                                     QuestionRepository questionRepository,
                                     AnswerRepository answerRepository) {
        this.answerService = answerService;
        this.quizResolutionRepository = quizResolutionRepository;
        this.quizService = quizService;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    @Override
    public QuizResolutionDto createQuizResolution(QuizResolutionCreateDto quizResolutionDto) throws InvalidContentException, NotFoundException {
        int correctAnswers = 0;
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Quiz quiz = quizService.findById(quizResolutionDto.getQuizId());

        QuizResolution quizResolution = new QuizResolution();
        quizResolution.setUser(user);
        quizResolution.setQuiz(quiz);
        quizResolution.setResolutionDateTime(LocalDateTime.now());


        // Check the number of answers is correct
        if (quiz.getQuestions().size() != quizResolutionDto.getResolvedQuestions().size())
            throw new InvalidContentException("Some questions were not answered");

        for (ResolvedQuestionDto resolvedQuestionDto : quizResolutionDto.getResolvedQuestions()) {
            if (resolvedQuestionDto.getSelectedAnswersIds().size() == questionRepository.findById(resolvedQuestionDto.getQuestionId()).get().getAnswers().stream().filter(Answer::isCorrect).count()) {
                if (!resolvedQuestionDto.getSelectedAnswersIds().stream().allMatch(answerId -> questionRepository.findById(resolvedQuestionDto.getQuestionId()).get().getAnswers().stream().anyMatch(answer -> answer.getId().equals(answerId)))){
                    throw new InvalidContentException("An answer is not related to the question");
                }
                if (resolvedQuestionDto.getSelectedAnswersIds().stream().allMatch(answerId -> answerRepository.findById(answerId).get().isCorrect())) {
                    correctAnswers++;
                }
            }
        }
        quizResolution.setCorrectAnswers(correctAnswers);
        quizResolutionRepository.save(quizResolution);
        return new QuizResolutionDto(quizResolution);
    }

}
