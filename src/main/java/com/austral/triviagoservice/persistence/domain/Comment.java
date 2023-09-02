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
}
