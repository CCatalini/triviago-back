package com.austral.triviagoservice.persistence.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String content;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonIgnore //json loop
    private Quiz quiz;

    public Question(){}



}
