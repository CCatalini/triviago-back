package com.austral.triviagoservice.commentTest;

import com.austral.triviagoservice.business.CommentService;
import com.austral.triviagoservice.business.QuizService;
import com.austral.triviagoservice.persistence.domain.Comment;
import com.austral.triviagoservice.persistence.domain.Quiz;
import com.austral.triviagoservice.presentation.dto.CommentDTO;
import com.austral.triviagoservice.presentation.dto.QuizCreate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class Responses {
    @Autowired
    CommentService commentService;
    @Autowired
    QuizService quizService;

    @Test
    public void commentResponses(){
        List<CommentDTO> listComment =  commentService.findAllByQuizId(1L);
        assert(listComment.size() == 1);
        assert(listComment.get(0).getResponses().size() == 2);
    }
}
