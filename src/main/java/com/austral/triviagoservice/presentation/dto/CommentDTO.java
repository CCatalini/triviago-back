package com.austral.triviagoservice.presentation.dto;

import lombok.Builder;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.List;
@Builder
public class CommentDTO {
    private String authorEmail;
    private String content;
    private LocalDateTime creationDate;
    private int likes;
    private List<CommentResponseDTO> responses;

    public CommentDTO() {}

    public CommentDTO(String authorEmail, String content, LocalDateTime creationDateTime, int likes, List<CommentResponseDTO> responses) {
        this.authorEmail = authorEmail;
        this.content = content;
        this.creationDate = creationDateTime;
        this.likes = likes;
        this.responses = responses;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDate;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDate = creationDateTime;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public List<CommentResponseDTO> getResponses() {
        return responses;
    }

    public void setResponses(List<CommentResponseDTO> responses) {
        this.responses = responses;
    }
}
