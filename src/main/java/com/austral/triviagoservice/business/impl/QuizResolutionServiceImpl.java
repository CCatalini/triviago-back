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
import com.austral.triviagoservice.persistence.repository.QuizResolutionRepository;
import com.austral.triviagoservice.presentation.dto.AnswerDto;
import com.austral.triviagoservice.presentation.dto.QuizResolutionCreateDto;
import com.austral.triviagoservice.presentation.dto.QuizResolutionDto;
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
    public QuizResolutionServiceImpl(AnswerService answerService, QuizResolutionRepository quizResolutionRepository, QuizService quizService) {
        this.answerService = answerService;
        this.quizResolutionRepository = quizResolutionRepository;
        this.quizService = quizService;
    }

    @Override
    public QuizResolutionDto createQuizResolution(QuizResolutionCreateDto quizResolutionDto) throws InvalidContentException, NotFoundException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Quiz quiz = quizService.findById(quizResolutionDto.getQuizId());

        // Check the number of answers is correct
        if (quiz.getQuestions().size() != quizResolutionDto.getSelectedAnswers().size())
            throw new InvalidContentException("Incorrect number of answers");

        List<Answer> answers = new ArrayList<>();
        for (AnswerDto selectedAnswer : quizResolutionDto.getSelectedAnswers()) {
            answers.add(answerService.findById(selectedAnswer.getId()));
        }

        // Check that all the answers are related to the quiz
        if(!answers.stream().allMatch(a -> a.getQuestion().getQuiz().getId().equals(quiz.getId())))
            throw new InvalidContentException("An answer is not related to the quiz");

        // Check that there are no answers related to the same question
        if (answers.stream().map(a -> a.getQuestion().getId()).distinct().count() < answers.size())
            throw new InvalidContentException("Two or more answers are related to the same question");

        QuizResolution quizResolution = new QuizResolution();
        quizResolution.setUser(user);
        quizResolution.setQuiz(quiz);
        quizResolution.setResolutionDateTime(LocalDateTime.now());
        quizResolution.setCorrectAnswers((int) answers.stream().filter(Answer::isCorrect).count());

        quizResolutionRepository.save(quizResolution);
        return new QuizResolutionDto(quizResolution);
    }

}
