package com.austral.triviagoservice.business;


import com.austral.triviagoservice.business.exception.InvalidContentException;
import com.austral.triviagoservice.persistence.domain.Comment;

import com.austral.triviagoservice.persistence.domain.Quiz;

import java.util.List;

public interface CommentService {
    List<Comment> findAllByQuizId(Long quizId);
    Comment create(Comment comment);
    Comment editComment(Comment comment, String newContent);
    Comment deleteComment(Comment comment);
    Comment findById(Long id) throws InvalidContentException;
    void editContent(Long id, String Content) throws InvalidContentException;
    void like(Long id, Boolean dislike, String token) throws InvalidContentException;
}
