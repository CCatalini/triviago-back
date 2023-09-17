package com.austral.triviagoservice.persistence.domain;


import com.austral.triviagoservice.presentation.dto.CommentCreateDto;
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
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private Long quizId;

    private String content;
    @Column(nullable = false, columnDefinition = "DATETIME(0)")
    private LocalDateTime creationDateTime;

    @Column
    private Integer likes;

    public Comment(CommentCreateDto commentDto){
        this.content = commentDto.getContent();
        this.creationDateTime = commentDto.getCreationDate();
        this.likes = commentDto.getLikes();
        this.userId = commentDto.getUserId();
        this.quizId = commentDto.getQuizId();
    }

    public Comment(){}

    public void incrementLike(){
        likes += 1;
    }

    public void decrementLike(){likes -= 1;}
}
