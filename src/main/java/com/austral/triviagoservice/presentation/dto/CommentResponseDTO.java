package com.austral.triviagoservice.presentation.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public class CommentResponseDTO {
    private String authorEmail;
    private String content;
    private LocalDateTime creationDateTime;
    private int likes;

    public CommentResponseDTO() {}

    public CommentResponseDTO(String authorEmail, String content, LocalDateTime creationDateTime, int likes) {
        this.authorEmail = authorEmail;
        this.content = content;
        this.creationDateTime = creationDateTime;
        this.likes = likes;
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
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
