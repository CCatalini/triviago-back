package com.austral.triviagoservice.presentation.dto;

import com.austral.triviagoservice.persistence.domain.CommentLike;
import com.austral.triviagoservice.persistence.domain.Comment;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
public class CommentCreateDto  {
    @NotNull
    private String content;
    @NotNull
    private Long quizId;
    private Long parentCommentId;

}
