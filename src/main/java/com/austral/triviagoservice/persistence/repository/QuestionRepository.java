package com.austral.triviagoservice.persistence.repository;

import com.austral.triviagoservice.persistence.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}