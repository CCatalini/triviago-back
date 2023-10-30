package com.austral.triviagoservice.persistence.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class User{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName", length = 50)
    private String firstName;

    @Column(name = "lastName", length = 50)
    private String lastName;

    @Column(name = "birthDate")
    private LocalDate birthDate;

    @Column
    private LocalDate creationDate;

    @Column(name = "email", length = 50, unique = true)
    private String email;

    @Column(name = "password", length = 50)
    private String password;

    @OneToMany(mappedBy="user", fetch = FetchType.EAGER)
    private List<CommentLike> likes;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    List<QuizRating> ratings;

    @ManyToMany
    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Quiz> savedQuizzes;



    @OneToMany(targetEntity = Quiz.class, cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    List<Quiz> quizzes = new ArrayList<>();

    @ManyToMany
    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<User> following = new ArrayList<>();

    public User() {
    }


    public void setLike(CommentLike like){
        likes.add(like);
    }

    public void quitLike(CommentLike like){
        likes.remove(like);
    }
}
