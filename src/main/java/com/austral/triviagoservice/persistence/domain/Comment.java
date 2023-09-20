package com.austral.triviagoservice.persistence.domain;


import com.austral.triviagoservice.presentation.dto.CommentCreateDto;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private Long answeredCommentId;


    public Comment(CommentCreateDto commentDto, Long userId){
        this.content = commentDto.getContent();
        this.creationDateTime = commentDto.getCreationDate();
        this.likes = commentDto.getLikes();
        this.userId = userId;
        this.quizId = commentDto.getQuizId();
        this.answeredCommentId = commentDto.getAnswerId();
    }

    public Comment(){}

    public void incrementLike(){
        likes += 1;
    }

    public void decrementLike(){
        likes -= 1;
    }
}
