package com.austral.triviagoservice.business;


import com.austral.triviagoservice.persistence.domain.Comment;

import com.austral.triviagoservice.persistence.domain.Quiz;

import java.util.List;

public interface CommentService {
    List<Comment> findAllByQuizId(Quiz quiz);
    Comment create(Comment comment);
    Comment editComment(Comment comment, String newContent);
    Comment deleteComment(Comment comment);
}
