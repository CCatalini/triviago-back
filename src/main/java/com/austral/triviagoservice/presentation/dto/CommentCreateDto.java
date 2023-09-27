package com.austral.triviagoservice.presentation.dto;


import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class CommentCreateDto  {
    @NotNull
    private String content;
    @NotNull
    private Long quizId;
    private Long parentCommentId;
}
