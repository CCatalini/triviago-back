package com.austral.triviagoservice.persistence.repository;

import com.austral.triviagoservice.persistence.domain.QuizRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRatingRepository extends JpaRepository<QuizRating, Long> {
    QuizRating findByUserIdAndQuizId(Long userId, Long quizId);
}
