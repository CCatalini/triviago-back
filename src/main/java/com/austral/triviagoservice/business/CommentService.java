package com.austral.triviagoservice.business;


import com.austral.triviagoservice.business.exception.InvalidContentException;
import com.austral.triviagoservice.business.exception.NotFoundException;
import com.austral.triviagoservice.persistence.domain.Comment;

import com.austral.triviagoservice.presentation.dto.CommentDto;
import com.austral.triviagoservice.presentation.dto.CommentCreateDto;


import java.util.List;

public interface CommentService {
    List<CommentDto> findAllByQuizId(Long quizId);
    Comment create(CommentCreateDto commentDto) throws NotFoundException;
    Comment editComment(Comment comment, String newContent);
    Comment deleteComment(Comment comment);
    Comment findById(Long id) throws InvalidContentException;
    void like(Long id, Boolean dislike) throws InvalidContentException;
    void removeLike(Long id) throws InvalidContentException;
}
