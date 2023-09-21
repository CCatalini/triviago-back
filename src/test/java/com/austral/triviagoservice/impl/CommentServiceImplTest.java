package com.austral.triviagoservice.impl;

import com.austral.triviagoservice.business.exception.InvalidContentException;
import com.austral.triviagoservice.business.impl.CommentLikeServiceImpl;
import com.austral.triviagoservice.business.impl.CommentServiceImpl;
import com.austral.triviagoservice.persistence.domain.Comment;
import com.austral.triviagoservice.persistence.domain.CommentLike;
import com.austral.triviagoservice.persistence.domain.User;
import com.austral.triviagoservice.persistence.repository.CommentLikeRepository;
import com.austral.triviagoservice.persistence.repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;


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
    @InjectMocks
    private CommentServiceImpl commentService; //Indica la clase de donde vienen las dependencias Mockeadas

    private Comment comment;
    private CommentLike commentLiked;
    private CommentLike commentDisliked;
    private User user;
    @BeforeEach
    void setUp(){

        user = new User();
        user.setId(1L);
        user.setEmail("hola@hola");
        user.setPassword("123");

        Authentication authentication = Mockito.mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(user);

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);

        SecurityContextHolder.setContext(securityContext);

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
        commentLiked.setUser(user);
        commentLiked.setComment(comment);

        commentDisliked = new CommentLike();
        commentDisliked.setId(2L);
        commentDisliked.setIsLike(false);
        commentDisliked.setUser(user);
        commentDisliked.setComment(comment);

    }

    @Test
    void likeEmptyCommentTest() throws InvalidContentException {
        when(commentRepository.findById(comment.getId())).thenReturn(Optional.ofNullable(comment)); //le explico al simulador cómo comportarse
        when(commentRepository.existsById(comment.getId())).thenReturn(true);
        when(commentLikeService.create(argThat(like ->
                like.getUser().equals(commentLiked.getUser()) &&
                        like.getComment().equals(commentLiked.getComment()) &&
                        like.getIsLike().equals(true)
        ))).thenReturn(commentLiked);

        assertEquals(commentService.findById(23L).getLikes().size(), 0); //should be an empty array
        commentService.like(comment.getId(), false);
        assertEquals(commentService.findById(23L).getLikes().size(), 1);

        CommentLike newLike = comment.getLikes().get(0);
        assertEquals(newLike, commentLiked);

        }


    @Test
    void dislikeTest() throws InvalidContentException {
        when(commentRepository.findById(comment.getId())).thenReturn(Optional.ofNullable(comment));
        when(commentRepository.existsById(comment.getId())).thenReturn(true);
       commentLiked.setIsLike(false);
        when(commentLikeService.create(argThat(like ->
                like.getUser().equals(commentLiked.getUser()) &&
                        like.getComment().equals(commentLiked.getComment()) &&
                        like.getIsLike().equals(false)
        ))).thenReturn(commentLiked);

        assertEquals(commentService.findById(23L).getLikes().size(), 0); //should be an empty array
        commentService.like(comment.getId(), true);
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

        commentService.like(comment.getId(), true);
        CommentLike actual = comment.getLikes().get(0); //same id
        assertNotNull(actual);
        assertEquals(actual.getIsLike(), false); //is disliked
    }

    @Test
    void likeADislikedCommentTest() throws InvalidContentException {
        when(commentRepository.findById(comment.getId())).thenReturn(Optional.ofNullable(comment));
        when(commentRepository.existsById(comment.getId())).thenReturn(true);

        comment.setLike(commentDisliked); //setsOneDislike

        commentService.like(comment.getId(), false);
        CommentLike actual = comment.getLikes().get(0); //same id
        assertNotNull(actual);
        assertEquals(actual.getIsLike(), true); //is disliked
    }

    @Test
    void InvalidDislikeADislikedCommentTest() throws InvalidContentException {
        when(commentRepository.findById(comment.getId())).thenReturn(Optional.ofNullable(comment));
        when(commentRepository.existsById(comment.getId())).thenReturn(true);
        comment.setLike(commentDisliked); //setsOneDislike

        commentService.like(comment.getId(), true); //dislike a disliked comment
        CommentLike actual = comment.getLikes().get(0); //same id
        assertNotNull(actual);
        assertEquals(actual.getIsLike(), false); //is still dislike
    }

    @Test
    void InvalidLikeALikedCommentTest() throws InvalidContentException {
        when(commentRepository.findById(comment.getId())).thenReturn(Optional.ofNullable(comment));
        when(commentRepository.existsById(comment.getId())).thenReturn(true);
        comment.setLike(commentLiked); //setsOnLike

        commentService.like(comment.getId(), false); //like a liked comment
        CommentLike actual = comment.getLikes().get(0); //same id
        assertNotNull(actual);
        assertEquals(actual.getIsLike(), true); //is still like
    }

    @Test
    void removeLikeTest() throws InvalidContentException {
        when(commentRepository.findById(comment.getId())).thenReturn(Optional.ofNullable(comment));
        when(commentRepository.existsById(comment.getId())).thenReturn(true);

        comment.setLike(commentLiked);
        assertEquals(comment.getLikes().size(), 1);
        commentService.removeLike(comment.getId());
        assertEquals(comment.getLikes().size(), 0);
    }
}