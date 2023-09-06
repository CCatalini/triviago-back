package com.austral.triviagoservice.business.impl;

import com.austral.triviagoservice.business.CommentService;
import com.austral.triviagoservice.persistence.domain.Comment;
import com.austral.triviagoservice.persistence.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
            this.commentRepository = commentRepository;
    }
        @Override
        public List<Comment> findAllByQuizId(Long quizId){
            return commentRepository.findByQuizId(quizId);
        }

        @Override
        public Comment create (Comment comment){
            comment.setCreationDateTime(LocalDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires")));
            commentRepository.save(comment);
            return comment;
        }

        @Override
        public Comment editComment (Comment comment, String newComment){
            commentRepository.findById(comment.getId()).ifPresent(comment1 -> {
                comment1.setContent(newComment);
                commentRepository.save(comment1);
            });
            return comment;
        }

        @Override
        public Comment deleteComment (Comment comment){
            commentRepository.delete(comment);
            return comment;
        }
}
