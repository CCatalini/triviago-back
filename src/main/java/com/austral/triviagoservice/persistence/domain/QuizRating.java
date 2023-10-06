package com.austral.triviagoservice.persistence.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class QuizRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer rating;
    @ManyToOne
    private User user;
    @ManyToOne
    private Quiz quiz;
}
