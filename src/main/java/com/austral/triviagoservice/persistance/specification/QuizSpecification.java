package com.austral.triviagoservice.persistance.specification;

import com.austral.triviagoservice.persistance.domain.Quiz;
import com.austral.triviagoservice.presentation.dto.QuizFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class QuizSpecification implements Specification<Quiz> {

    private final QuizFilter quizFilter;
    public QuizSpecification(QuizFilter quizFilter){
        this.quizFilter = quizFilter;
    }
    @Override
    public Predicate toPredicate(Root<Quiz> quiz, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> restrictions = new ArrayList<>();
        if(quizFilter.getTitle() != null && !quizFilter.getTitle().isEmpty()){
            restrictions.add(criteriaBuilder.equal(quiz.get("title"), quizFilter.getTitle()));
        }
        if(quizFilter.getCreationDate() != null){
            restrictions.add(criteriaBuilder.equal(quiz.get("creationDate"), quizFilter.getCreationDate()));
        }
        if((quizFilter.getDateFrom() != null) && (quizFilter.getDateTo() != null)){
            restrictions.add(criteriaBuilder.between(quiz.get("creationDate"),quizFilter.getDateFrom(), quizFilter.getDateTo()));
        }
        if(quizFilter.getQuestionQty() != null){
            restrictions.add(criteriaBuilder.equal(quiz.get("questionQty"), quizFilter.getQuestionQty()));
        }
        if((quizFilter.getMinQuestion() != null) && (quizFilter.getMaxQuestion()!= null)){
            restrictions.add(criteriaBuilder.between(quiz.get("questionQty"), quizFilter.getMinQuestion(), quizFilter.getMaxQuestion()));
        }
        if(quizFilter.getRating()!=null){
            restrictions.add(criteriaBuilder.equal(quiz.get("rating"), quizFilter.getRating()));
        }
        if((quizFilter.getMinRating() != null) && (quizFilter.getMaxRating()!=null)){
            restrictions.add(criteriaBuilder.between(quiz.get("rating"), quizFilter.getMinRating(), quizFilter.getMaxRating()));
        }
        if(quizFilter.getUserId() != null){
            restrictions.add(criteriaBuilder.equal(quiz.get("userId"), quizFilter.getUserId()));
        }
        return criteriaBuilder.and(restrictions.toArray(new Predicate[0]));
    }
}
