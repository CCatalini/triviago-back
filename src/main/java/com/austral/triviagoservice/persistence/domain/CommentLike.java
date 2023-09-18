package com.austral.triviagoservice.persistence.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter

@Entity
public class CommentLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long commentId;

    private Boolean isLike;

    public CommentLike(){}

    public CommentLike(Long userId, Long commentId, Boolean isLike) {
        this.userId = userId;
        this.commentId = commentId;
        this.isLike = isLike;
    }
}
