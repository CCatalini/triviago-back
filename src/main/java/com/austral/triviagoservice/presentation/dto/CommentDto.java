package com.austral.triviagoservice.presentation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Builder
@Setter
@Getter
public class CommentDto {
    private Long id;
    private AuthorDto author;
    private String content;
    private String creationDate;
    private int likes;
    private List<CommentDto> responses;
    private Long parentCommentId;

}
