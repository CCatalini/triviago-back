package com.austral.triviagoservice.presentation.dto;

import lombok.Builder;

import java.time.LocalDate;

import java.util.List;
@Builder
public class CommentDTO {
    private Long id;
    private String authorEmail;
    private String content;
    private LocalDate creationDate;
    private int likes;
    private List<CommentResponseDTO> responses;

    public CommentDTO() {}

    public CommentDTO(Long id, String authorEmail, String content, LocalDate creationDateTime, int likes, List<CommentResponseDTO> responses) {
        this.id = id;
        this.authorEmail = authorEmail;
        this.content = content;
        this.creationDate = creationDateTime;
        this.likes = likes;
        this.responses = responses;

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
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

    public LocalDate getCreationDateTime() {
        return creationDate;
    }

    public void setCreationDateTime(LocalDate creationDateTime) {
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
