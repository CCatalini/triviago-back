package com.austral.triviagoservice.persistence.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    private LocalDateTime creationDateTime;

    @Column
    private Integer likes;

    public Comment(Long userId, Long quizId, String content) {
        this.userId = userId;
        this.quizId = quizId;
        this.content = content;
        this.creationDateTime = LocalDateTime.now();
    }
    public Comment(){}

    public void incrementLike(){
        likes += 1;
    }
}
