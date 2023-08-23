package com.austral.triviagoservice.business;


import com.austral.triviagoservice.persistence.domain.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> findAll();
    Comment create(Comment comment);
    Comment editComment(Comment comment, String newContent);
    Comment deleteComment(Comment comment);
}
