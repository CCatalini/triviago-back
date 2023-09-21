package com.austral.triviagoservice.persistence.domain;


import com.austral.triviagoservice.presentation.dto.CommentCreateDto;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @OneToOne
    private Comment parentComment;

    public Comment(CommentCreateDto commentDto){
        this.content = commentDto.getContent();
        this.creationDateTime = commentDto.getCreationDate();
        this.likes = commentDto.getLikes();
        this.userId = commentDto.getUserId();
        this.quizId = commentDto.getQuizId();
        this.replies = new ArrayList<>();
        this.parentComment =  commentDto.getParentComment();
    }

    public Comment(){}

    public void incrementLike(){
        likes += 1;
    }

    public void decrementLike(){
        likes -= 1;
    }

}
