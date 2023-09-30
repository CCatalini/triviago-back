package com.austral.triviagoservice.persistence.repository;

import com.austral.triviagoservice.persistence.domain.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long>, JpaSpecificationExecutor<Quiz> {
    Optional<Quiz> findByInvitationCode(String invitationCode);

    @Modifying
    @Query(value = "DELETE FROM quiz WHERE id = ?1 AND user_id = ?2", nativeQuery = true)
    int deleteMyQuizById(Long quizId, Long userId);

}
