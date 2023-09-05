package com.austral.triviagoservice.persistence.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
@Getter
@Setter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long quizId;

    private String content;


    
    private Long answeredCommentId;

    public Comment(Long userId, Long quizId, String content, Long answeredCommentId) {
        this.userId = userId;
        this.quizId = quizId;
        this.content = content;
        this.creationDateTime = LocalDate.now();
        this.likes = 0;
        this.answeredCommentId = answeredCommentId;
    }
    private LocalDate creationDate;

    @Column
    private Integer likes;


    public Comment(){}

    public void incrementLike(){
        likes += 1;
    }

    public void decrementLike(){
        likes -= 1;
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
