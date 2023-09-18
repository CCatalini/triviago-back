package com.austral.triviagoservice.impl;

import com.austral.triviagoservice.business.exception.InvalidContentException;
import com.austral.triviagoservice.business.impl.CommentLikeServiceImpl;
import com.austral.triviagoservice.business.impl.CommentServiceImpl;
import com.austral.triviagoservice.persistence.domain.Comment;
import com.austral.triviagoservice.persistence.domain.CommentLike;
import com.austral.triviagoservice.persistence.repository.CommentLikeRepository;
import com.austral.triviagoservice.persistence.repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {
    @Mock
    private CommentRepository commentRepository; //Crea un objeto que simula un repositorio
    @Mock
    private CommentLikeServiceImpl commentLikeService;
    @Mock
    private CommentLikeRepository commentLikeRepository;
    @InjectMocks
    private CommentServiceImpl commentService; //Indica la clase de donde vienen las dependencias Mockeadas

    private Comment comment;

    private CommentLike commentLiked;
    private CommentLike commentDisliked;

    private final String token = "eyJhbGciOiJIUzUxMiJ9.eyJmaXJzdE5hbWUiOiJtYXJrIiwibGFzdE5hbWUiOiJsYW1iIiwic3ViIjoibWFyayIsImlkIjoxLCJleHAiOjE2OTQ4MTQ4ODIsImJpcnRoRGF0ZSI6IjIwMDItMDYtMjMiLCJpYXQiOjE2OTQ3Mjg0ODJ9.91jsS6pzzV4YAv2MAyYR1nCwBsSoVgVR3mA9ocSLbpizG0hOXOwipuuFKF8o1VD-_VFd2MaWFm6flJcTTW7DUw";

    @BeforeEach
    void setUp(){
        comment = new Comment();  //define un comentario
        comment.setId(23L);
        comment.setUserId(1L);
        comment.setLikes(new ArrayList<>());
        comment.setCreationDateTime(null);
        comment.setQuizId(4567L);
        comment.setContent("soy un comentario :)");

        commentLiked = new CommentLike();
        commentLiked.setId(2L);
        commentLiked.setIsLike(true);
        commentLiked.setCommentId(23L);
        commentLiked.setUserId(1L);

        commentDisliked = new CommentLike();
        commentDisliked.setId(2L);
        commentDisliked.setIsLike(false);
        commentDisliked.setCommentId(23L);
        commentDisliked.setUserId(1L);

    }
    @Test
    void likeEmptyCommentTest() throws InvalidContentException {
        when(commentRepository.findById(comment.getId())).thenReturn(Optional.ofNullable(comment)); //le explico al simulador cómo comportarse
        when(commentRepository.existsById(comment.getId())).thenReturn(true);
        CommentLike dto = new CommentLike(commentLiked.getUserId(),commentLiked.getCommentId(), true);
        when(commentLikeService.creat(argThat(like ->
                dto.getUserId().equals(commentLiked.getUserId()) &&
                        dto.getCommentId().equals(commentLiked.getCommentId()) &&
                            dto.getIsLike().equals(true)
        ))).thenReturn(commentLiked);

        assertEquals(commentService.findById(23L).getLikes().size(), 0); //should be an empty array
        commentService.like(comment.getId(), false, token);
        assertEquals(commentService.findById(23L).getLikes().size(), 1);

        CommentLike newLike = comment.getLikes().get(0);
        assertEquals(newLike, commentLiked);
    }


    @Test
    void dislikeTest() throws InvalidContentException {
        when(commentRepository.findById(comment.getId())).thenReturn(Optional.ofNullable(comment));
        when(commentRepository.existsById(comment.getId())).thenReturn(true);
        CommentLike dto = new CommentLike(commentLiked.getUserId(),commentLiked.getCommentId(), false);
        commentLiked.setIsLike(false);
        when(commentLikeService.creat(argThat(like ->
                dto.getUserId().equals(commentLiked.getUserId()) &&
                        dto.getCommentId().equals(commentLiked.getCommentId()) &&
                        dto.getIsLike().equals(false)
        ))).thenReturn(commentLiked);

        assertEquals(commentService.findById(23L).getLikes().size(), 0); //should be an empty array
        commentService.like(comment.getId(), true, token);
        assertEquals(commentService.findById(23L).getLikes().size(), 1);

        CommentLike newLike = comment.getLikes().get(0);
        assertNotNull(newLike);
        assertEquals(newLike.getIsLike(), false); //is dislike
    }


    @Test
    void dislikeALikedCommentTest() throws InvalidContentException {
        when(commentRepository.findById(comment.getId())).thenReturn(Optional.ofNullable(comment));
        when(commentRepository.existsById(comment.getId())).thenReturn(true);

        comment.setLike(commentLiked); //setsOneLike

        commentService.like(comment.getId(), true, token);
        CommentLike actual = comment.getLikes().get(0); //same id
        assertNotNull(actual);
        assertEquals(actual.getIsLike(), false); //is disliked
    }

    @Test
    void likeADislikedCommentTest() throws InvalidContentException {
        when(commentRepository.findById(comment.getId())).thenReturn(Optional.ofNullable(comment));
        when(commentRepository.existsById(comment.getId())).thenReturn(true);

        comment.setLike(commentDisliked); //setsOneLike

        commentService.like(comment.getId(), false, token);
        CommentLike actual = comment.getLikes().get(0); //same id
        assertNotNull(actual);
        assertEquals(actual.getIsLike(), true); //is disliked

    }
}