package com.austral.triviagoservice.presentation.dto;

import com.austral.triviagoservice.persistence.domain.Comment;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class CommentCreateDto  {
    @NotNull
    private String content;
    @NotNull
    private Long quizId;
    private LocalDateTime creationDate;
    private int likes;
    private Long parentComment;
}
