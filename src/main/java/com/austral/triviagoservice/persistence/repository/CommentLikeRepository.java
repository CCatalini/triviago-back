package com.austral.triviagoservice.persistence.repository;

import com.austral.triviagoservice.persistence.domain.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
}
