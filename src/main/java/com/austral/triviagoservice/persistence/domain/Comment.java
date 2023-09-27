package com.austral.triviagoservice.persistence.domain;


import com.austral.triviagoservice.presentation.dto.CommentCreateDto;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private Long quizId;
    private String content;
    @Column(nullable = false, columnDefinition = "DATETIME(0)")
    private LocalDateTime creationDateTime;
    @Column
    private Integer likes;
    @OneToMany
    private List<Comment> replies;
    private Long parentCommentId;

    @OneToMany(mappedBy="comment")
    private List<CommentLike> likes;

    public Comment(CommentCreateDto commentDto, Long userId){
        this.content = commentDto.getContent();
        this.creationDateTime = commentDto.getCreationDate();
        this.likes = commentDto.getLikes();
        this.userId = userId;
        this.quizId = commentDto.getQuizId();
        this.replies = new ArrayList<>();
        this.parentCommentId =  commentDto.getParentCommentId();
    }

    public Comment(){}

    public void setLike(CommentLike like){
        likes.add(like);
    }
    public void quitLike(CommentLike like){
        likes.remove(like);
    }
}
