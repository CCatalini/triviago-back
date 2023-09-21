package com.austral.triviagoservice.presentation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.List;
@Builder
@Setter
@Getter
public class CommentDTO {
    private Long id;
    private AuthorDto author;
    private String content;
    private String creationDate;
    private int likes;
    private List<CommentDTO> responses;

}
