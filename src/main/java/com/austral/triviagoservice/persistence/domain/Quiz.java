package com.austral.triviagoservice.persistence.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private String title;
    @Column
    private String description;
    @Column(nullable = false)
    private LocalDate creationDate;
    @Column
    private double rating;
    @Column
    private String invitationCode;
    @Column
    private Boolean isPrivate;


    @OneToMany(targetEntity = Question.class, cascade = CascadeType.ALL)
    @JsonIgnore //json loop
    List<Question> questions;

    @ManyToMany(mappedBy = "quizzes")
    List<Label> labels;
  
    public Quiz(){}
}
