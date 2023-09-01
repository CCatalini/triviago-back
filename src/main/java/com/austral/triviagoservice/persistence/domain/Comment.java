package com.austral.triviagoservice.persistence.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long quizId;

    private String content;

    private LocalDateTime creationDateTime;
    private int likes;
    private Long answeredCommentId;

    public Comment(Long userId, Long quizId, String content, Long answeredCommentId) {
        this.userId = userId;
        this.quizId = quizId;
        this.content = content;
        this.creationDateTime = LocalDateTime.now();
        this.likes = 0;
        this.answeredCommentId = answeredCommentId;
    }

    public Comment(){}

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
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

    public Long getAnsweredCommentId() {
        return answeredCommentId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setAnsweredCommentId(Long answeredCommentId) {
        this.answeredCommentId = answeredCommentId;
    }
}
