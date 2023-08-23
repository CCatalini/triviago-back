package com.austral.triviagoservice.business.impl;

import com.austral.triviagoservice.business.CommentService;
import com.austral.triviagoservice.persistence.domain.Comment;
import com.austral.triviagoservice.persistence.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();

    }

    @Override
    public Comment create(Comment comment) {
        commentRepository.save(comment);
        return comment;
    }

    @Override
    public Comment editComment(Comment comment, String newComment) {
        commentRepository.findById(comment.getId()).ifPresent(comment1 -> {
            comment1.setContent(newComment);
            commentRepository.save(comment1);
        });
        return comment;
    }

    @Override
    public Comment deleteComment(Comment comment) {
        commentRepository.delete(comment);
        return comment;
    }
}
