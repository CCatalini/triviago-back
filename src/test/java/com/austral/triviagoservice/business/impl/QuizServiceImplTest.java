package com.austral.triviagoservice.business.impl;

import com.austral.triviagoservice.business.exception.InvalidContentException;
import com.austral.triviagoservice.persistence.domain.*;
import com.austral.triviagoservice.persistence.repository.QuizRepository;
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
class QuizServiceImplTest {

    @Mock
    private QuizRatingServiceImpl quizRatingService;
    @Mock
    private QuizRepository quizRepository;

    @InjectMocks
    private QuizServiceImpl quizService;

    private Quiz quiz;
    private User user;
    private QuizRating quizRating;

    @BeforeEach
    void setUp(){
        user = new User(); user.setId(1L); user.setEmail("hola@hola"); user.setPassword("123");user.setRaiting(new ArrayList<>());
        quiz = new Quiz(); quiz.setTitle("Quiz"); quiz.setId(23L); quiz.setRating(new ArrayList<>());
        quizRating = new QuizRating(); quizRating.setQuiz(quiz); quizRating.setUser(user); quizRating.setId(2L);quizRating.setRating(3);
    }


    @Test
    void rate_Quiz_Test() throws InvalidContentException {
        when(quizRepository.findById(23L)).thenReturn(Optional.ofNullable(quiz));
        when(quizRepository.existsById(23L)).thenReturn(true);

        Authentication authentication = Mockito.mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(user);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);


        when(quizRatingService.create(argThat(like ->
                like.getUser().equals(quizRating.getUser()) &&
                        like.getQuiz().equals(quizRating.getQuiz())
        ))).thenReturn(quizRating);

        assertTrue(quiz.getRating().isEmpty());
        quizService.rateQuiz(quiz.getId(),3);
        assertFalse(quiz.getRating().isEmpty());
        assertEquals(quiz.getRating().stream().findFirst().get().getRating(), 3);
    }

    @Test
    void rate_Quiz_Already_Rated_Test() throws InvalidContentException {
        when(quizRepository.findById(23L)).thenReturn(Optional.ofNullable(quiz));
        when(quizRepository.existsById(23L)).thenReturn(true);

        Authentication authentication = Mockito.mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(user);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        quiz.getRating().add(quizRating);
        assertEquals(quiz.getRating().size(), 1);
        int newRate = 5;
        quizService.rateQuiz(23L, newRate);
        assertEquals(quiz.getRating().size(),1);
        assertNotEquals(quiz.getRating().stream().findFirst().get().getRating(), 3);
        assertEquals(quiz.getRating().stream().findFirst().get().getRating(), newRate);
    }

    @Test
    void rate_Quiz_With_Invalid_Id_Test(){
        when(quizRepository.existsById(3L)).thenReturn(false);

        try{
            quizService.rateQuiz(3L, 2);
        } catch (InvalidContentException e) {
            assertTrue(quiz.getRating().isEmpty()); //still empty
        }
    }

    @Test
    void rate_Quiz_With_Invalid_Rate_Less_Than_One_Test(){
        try{
            quizService.rateQuiz(23L, 0);
        }
        catch (InvalidContentException e){
            assertTrue(quiz.getRating().isEmpty()); //quiz has no rate
        }
    }

    @Test
    void rate_Quiz_With_Invalid_Rate_Greater_Than_Five_Test(){
        try{
            quizService.rateQuiz(23L, 6);
        }
        catch (InvalidContentException e){
            assertTrue(quiz.getRating().isEmpty());
        }
    }
}