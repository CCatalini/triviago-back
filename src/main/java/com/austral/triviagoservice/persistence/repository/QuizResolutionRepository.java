package com.austral.triviagoservice.persistence.repository;

import com.austral.triviagoservice.persistence.domain.QuizResolution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizResolutionRepository extends JpaRepository<QuizResolution, Long> {

    List<QuizResolution> findAllByQuizId(Long quizId);
}