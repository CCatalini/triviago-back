package com.austral.triviagoservice.business.impl;

import com.austral.triviagoservice.business.helper.ErrorCheckers;
import com.austral.triviagoservice.business.QuizService;
import com.austral.triviagoservice.business.exception.InvalidContentException;
import com.austral.triviagoservice.persistence.domain.*;
import com.austral.triviagoservice.persistence.repository.LabelRepository;
import com.austral.triviagoservice.persistence.repository.QuizRepository;
import com.austral.triviagoservice.persistence.specification.QuizSpecification;
import com.austral.triviagoservice.presentation.dto.AnswerCreateDto;
import com.austral.triviagoservice.presentation.dto.QuizDto;
import com.austral.triviagoservice.presentation.dto.QuizCreateDto;
import com.austral.triviagoservice.presentation.dto.QuizFilter;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {

    final private QuizRepository quizRepository;
    private final LabelRepository labelRepository;

    public QuizServiceImpl(QuizRepository quizRepository,
                           LabelRepository labelRepository) {
        this.quizRepository = quizRepository;
        this.labelRepository = labelRepository;
    }

    @Override
    public QuizDto findById(Long id) throws InvalidContentException {
        Optional<Quiz> search = quizRepository.findById(id);
        if(search.isPresent()){
            Quiz quiz = search.get();
            return QuizDto.createDto(quiz);

        }
        throw new InvalidContentException("Invalid quiz Id");
    }

    @Override
    public Page<Quiz> findAll(QuizFilter filter, Pageable pageable) throws InvalidContentException {
        ErrorCheckers checker = new ErrorCheckers();
        checker.checkQuizFilter(filter); //checks for invalid content on filter

        final QuizSpecification specification = new QuizSpecification(filter);
        List<Quiz> quizzes = quizRepository.findAll(specification);

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Quiz> pageQuizzes;

        if (quizzes.size() < startItem) {
            pageQuizzes = List.of(); //Por si no hay suficientes elementos para llenar esa página
        } else {
            int toIndex = Math.min(startItem + pageSize, quizzes.size());
            pageQuizzes = quizzes.subList(startItem, toIndex); //Si es menor, crea la sublista
        }


        return new PageImpl<>(pageQuizzes, pageable, quizzes.size());
    }

    @Override
    public QuizDto create(QuizCreateDto quizCreateDto) throws InvalidContentException {
        if (quizCreateDto.getTitle() == null) throw new InvalidContentException("Invalid title");
        if (quizCreateDto.getDescription() == null) throw new InvalidContentException("Invalid description");
        if (quizCreateDto.getQuestions().isEmpty()) throw new InvalidContentException("Invalid questions quantity");
        if (quizCreateDto.getQuestions().stream().anyMatch(q -> q.getContent() == null))
            throw new InvalidContentException("Invalid question content");
        if (quizCreateDto.getQuestions().stream().anyMatch(q -> q.getAnswers().stream().anyMatch(a -> a.getContent() == null)))
            throw new InvalidContentException("Invalid answer content");
        if (quizCreateDto.getQuestions().stream().anyMatch(q -> q.getAnswers().size() < 2))
            throw new InvalidContentException("Invalid answers quantity, there must be at least two possible answers for each question");
        if (quizCreateDto.getQuestions().stream().anyMatch(q -> q.getAnswers().stream().noneMatch(AnswerCreateDto::isCorrect)))
            throw new InvalidContentException("Invalid answers, there must be at least one correct answer for each question");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Quiz quiz = new Quiz(quizCreateDto, user.getId());
        quizRepository.save(quiz);
        return QuizDto.createDto(quiz);
    }



    @Override
    public Long deleteById(Long id) throws InvalidContentException {
        if(quizRepository.existsById(id)){
            quizRepository.deleteById(id);
            return id;
        }
        throw new InvalidContentException("Invalid Id, quiz does not exist");

    }

    @Override
    public QuizDto findByInvitationCode(String invitationCode) throws InvalidContentException{
        Optional<Quiz> search = quizRepository.findByInvitationCode(invitationCode);
        if (search.isPresent()){
            Quiz quiz = search.get();
            return QuizDto.createDto(quiz);
        }
        throw new InvalidContentException("Invalid invitation Code");
    }
}
