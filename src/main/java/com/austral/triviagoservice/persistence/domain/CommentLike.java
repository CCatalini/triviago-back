package com.austral.triviagoservice.persistence.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@Entity
public class CommentLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Comment comment;

    private Boolean isLike;

    public CommentLike(){}

    public CommentLike(User user, Comment comment, Boolean isLike) {
        this.user = user;
        this.comment = comment;
        this.isLike = isLike;
    }
}
