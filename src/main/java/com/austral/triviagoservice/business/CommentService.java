package com.austral.triviagoservice.business;


import com.austral.triviagoservice.business.exception.InvalidContentException;
import com.austral.triviagoservice.business.exception.NotFoundException;
import com.austral.triviagoservice.persistence.domain.Comment;

import com.austral.triviagoservice.persistence.domain.Quiz;
import com.austral.triviagoservice.presentation.dto.CommentDTO;
import com.austral.triviagoservice.presentation.dto.CommentCreateDto;

import java.util.List;

public interface CommentService {
    List<Comment> findAllByQuizId(Long quizId);
    CommentDTO create(CommentCreateDto commentDto) throws NotFoundException;
    Comment editComment(Comment comment, String newContent);
    Comment deleteComment(Comment comment);
    void like(Long id, Boolean dislike) throws InvalidContentException;
    Comment findById(Long id) throws InvalidContentException;
    CommentDTO findCommentAndAnswers(Long id);
    List<CommentDTO> findAllCommentsAndAnswersByQuiz(Long QuizId);
}
