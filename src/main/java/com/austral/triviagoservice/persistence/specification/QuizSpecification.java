package com.austral.triviagoservice.persistence.specification;

import com.austral.triviagoservice.persistence.domain.Quiz;
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
            restrictions.add(criteriaBuilder.like(
                criteriaBuilder.lower(quiz.get("title")), 
                "%" + quizFilter.getTitle().toLowerCase() + "%"
            ));
        }
        if(quizFilter.getCreationDate()!=null){
            restrictions.add(criteriaBuilder.equal(quiz.get("creationDate"), quizFilter.getCreationDate()));    
        }
        else{
            if((quizFilter.getDateFrom() != null) && (quizFilter.getDateTo() != null)){
                restrictions.add(criteriaBuilder.between(quiz.get("creationDate"),quizFilter.getDateFrom(), quizFilter.getDateTo()));
            }
            else{
                if (quizFilter.getDateFrom() != null) {
                    restrictions.add(criteriaBuilder.greaterThanOrEqualTo(quiz.get("creationDate"), quizFilter.getDateFrom()));
                }
                else if (quizFilter.getDateTo() != null) {
                    restrictions.add(criteriaBuilder.lessThanOrEqualTo(quiz.get("creationDate"), quizFilter.getDateTo()));
                }
            }
        }
        // Note: questionQty and rating filters are handled in QuizServiceImpl 
        // because these are calculated fields, not database columns
        if(quizFilter.getUserId() != null){
            restrictions.add(criteriaBuilder.equal(quiz.get("user").get("id"), quizFilter.getUserId()));
        }
        if (quizFilter.getIsPrivate() != null) {
            restrictions.add(criteriaBuilder.equal(quiz.get("isPrivate"), quizFilter.getIsPrivate()));
        }
        return criteriaBuilder.and(restrictions.toArray(new Predicate[0]));
    }
}
