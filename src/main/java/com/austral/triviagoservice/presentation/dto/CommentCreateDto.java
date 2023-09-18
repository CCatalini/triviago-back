package com.austral.triviagoservice.presentation.dto;

import com.austral.triviagoservice.persistence.domain.CommentLike;
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
    @NotNull
    private Long userId;
    private LocalDateTime creationDate;
    private List<CommentLike> likes;

}
