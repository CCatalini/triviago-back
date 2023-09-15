package com.austral.triviagoservice.impl;

import com.austral.triviagoservice.business.exception.InvalidContentException;
import com.austral.triviagoservice.business.impl.CommentServiceImpl;
import com.austral.triviagoservice.persistence.domain.Comment;
import com.austral.triviagoservice.persistence.repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {
    @Mock
    private CommentRepository commentRepository; //Crea un objeto que simula un repositorio
    @InjectMocks
    private CommentServiceImpl commentService; //Indica la clase de donde vienen las dependencias Mockeadas

    private Comment comment;

    private final String token = "eyJhbGciOiJIUzUxMiJ9.eyJmaXJzdE5hbWUiOiJtYXJrIiwibGFzdE5hbWUiOiJsYW1iIiwic3ViIjoibWFyayIsImlkIjoxLCJleHAiOjE2OTQ4MTQ4ODIsImJpcnRoRGF0ZSI6IjIwMDItMDYtMjMiLCJpYXQiOjE2OTQ3Mjg0ODJ9.91jsS6pzzV4YAv2MAyYR1nCwBsSoVgVR3mA9ocSLbpizG0hOXOwipuuFKF8o1VD-_VFd2MaWFm6flJcTTW7DUw";


    @BeforeEach
    void setUp(){
        comment = new Comment();  //define un comentario
        comment.setId(23L);
        comment.setUserId(1L);
        comment.setLikes(0);
        comment.setCreationDateTime(null);
        comment.setQuizId(4567L);
        comment.setContent("soy un comentario :)");
    }
    @Test
    void like() throws InvalidContentException {
        when(commentRepository.findById(comment.getId())).thenReturn(Optional.ofNullable(comment)); //le explico al simulador cómo comportarse
        when(commentRepository.existsById(comment.getId())).thenReturn(true);

        assertEquals(commentService.findById(23L).getLikes(), 0);
        commentService.like(comment.getId(), false); //likes a comment, userId equals 1L
        assertEquals(commentService.findById(23L).getLikes(), 1);
    }

    @Test
    void likeNegativeTest() throws InvalidContentException {
        when(commentRepository.findById(comment.getId())).thenReturn(Optional.ofNullable(comment));
        when(commentRepository.existsById(comment.getId())).thenReturn(true);

        comment.setLikes(-2);
        commentService.like(comment.getId(), false);
        assertEquals(comment.getLikes(), -1);
    }


    @Test
    void likeZeroToMinusOneTest() throws InvalidContentException {
        when(commentRepository.findById(comment.getId())).thenReturn(Optional.ofNullable(comment));
        when(commentRepository.existsById(comment.getId())).thenReturn(true);

        comment.setLikes(-1);
        commentService.like(comment.getId(), true);
        assertEquals(comment.getLikes(), 0);
    }

    @Test
    void likeOneToZeroTest() throws InvalidContentException {
        when(commentRepository.findById(comment.getId())).thenReturn(Optional.ofNullable(comment)); //le explico al simulador cómo comportarse
        when(commentRepository.existsById(comment.getId())).thenReturn(true);

        assertEquals(comment.getLikes(), 1);
        commentService.like(comment.getId(), true); //likes a comment, userId equals 1L
        assertEquals(comment.getLikes(), 0);
    }
}