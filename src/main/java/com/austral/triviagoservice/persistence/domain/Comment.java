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

    public Comment(CommentCreateDto commentDto, Long userId){
        this.content = commentDto.getContent();
        this.creationDateTime = LocalDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires"));
        this.likes = 0;
        this.userId = userId;
        this.quizId = commentDto.getQuizId();
        this.replies = new ArrayList<>();
        this.parentCommentId =  commentDto.getParentCommentId();
    }

    public Comment(){}

    public void incrementLike(){
        likes += 1;
    }

    public void decrementLike(){
        likes -= 1;
    }

}
