package com.austral.triviagoservice.persistence.repository;

import com.austral.triviagoservice.persistence.domain.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long>, JpaSpecificationExecutor<Quiz> {
    Optional<Quiz> findByInvitationCode(String invitationCode);

    List<Quiz> findAllByIsPrivateFalse();
}
