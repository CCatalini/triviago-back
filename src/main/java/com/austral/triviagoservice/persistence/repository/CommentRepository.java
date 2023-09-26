package com.austral.triviagoservice.persistence.repository;

import com.austral.triviagoservice.persistence.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByQuizId(Long quizId);
}
    
