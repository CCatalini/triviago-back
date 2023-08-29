package com.austral.triviagoservice.business.impl;

import com.austral.triviagoservice.business.helper.ErrorCheckers;
import com.austral.triviagoservice.business.QuizService;
import com.austral.triviagoservice.business.exception.InvalidContentException;
import com.austral.triviagoservice.persistence.domain.Quiz;
import com.austral.triviagoservice.persistence.repository.QuizRepository;
import com.austral.triviagoservice.persistence.specification.QuizSpecification;
import com.austral.triviagoservice.presentation.dto.QuizCreate;
import com.austral.triviagoservice.presentation.dto.QuizFilter;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class QuizServiceImpl implements QuizService {

    final private QuizRepository quizRepository;
    public QuizServiceImpl(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override
    public QuizCreate findById(Long id) throws InvalidContentException {
        Optional<Quiz> search = quizRepository.findById(id);
        if(search.isPresent()){
            Quiz quiz = search.get();
            return QuizCreate.createDTO(quiz);

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
    public QuizCreate createQuiz(Quiz quiz) {
        quiz.setCreationDate(LocalDate.now(ZoneId.of("America/Argentina/Buenos_Aires"))); //Buenos Aires time zone
        if(quiz.getIsPrivate()){
            //Se genera un código de invitación con un UUID random.
            UUID code = UUID.randomUUID();
            quiz.setInvitationCode(code.toString());
        }
        Quiz created = quizRepository.save(quiz);
        return QuizCreate.createDTO(created);
    }

    @Override
    public Long deleteById(Long id) throws InvalidContentException {
        if(this.isPresent(id)){
            quizRepository.deleteById(id);
            return id;
        }
        throw new InvalidContentException("Invalid Id, quiz does not exist");

    }

    @Override
    public Boolean isPresent(Long id){
        Optional<Quiz> entity = quizRepository.findById(id);
        return entity.isPresent();
    }
}
