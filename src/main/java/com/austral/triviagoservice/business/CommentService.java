package com.austral.triviagoservice.business;


import com.austral.triviagoservice.business.exception.InvalidContentException;
import com.austral.triviagoservice.business.exception.NotFoundException;
import com.austral.triviagoservice.business.exception.UnauthorizedException;
import com.austral.triviagoservice.persistence.domain.Comment;

import com.austral.triviagoservice.presentation.dto.CommentDto;
import com.austral.triviagoservice.presentation.dto.CommentCreateDto;
import com.austral.triviagoservice.presentation.dto.CommentEditDto;
import com.austral.triviagoservice.presentation.dto.TotalLikesDto;


import java.util.List;

public interface CommentService {
    List<CommentDto> findAllByQuizId(Long quizId);
    CommentDto create(CommentCreateDto commentDto) throws NotFoundException;
    CommentDto editComment(Long id, CommentEditDto commentEditDto) throws NotFoundException, UnauthorizedException;
    void deleteComment(Long id) throws NotFoundException, UnauthorizedException;
    Comment findById(Long id) throws InvalidContentException;
    TotalLikesDto like(Long id, Boolean dislike) throws InvalidContentException;
    TotalLikesDto removeLike(Long id) throws InvalidContentException;
}
