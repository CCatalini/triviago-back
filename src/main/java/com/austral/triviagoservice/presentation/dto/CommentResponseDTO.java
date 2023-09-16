package com.austral.triviagoservice.presentation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Setter
@Getter
public class CommentResponseDTO {
    private AuthorDto author;
    private String content;
    private LocalDateTime creationDateTime;
    private int likes;
}
