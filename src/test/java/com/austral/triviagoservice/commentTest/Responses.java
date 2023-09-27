package com.austral.triviagoservice.commentTest;

import com.austral.triviagoservice.business.CommentService;
import com.austral.triviagoservice.business.QuizService;
import com.austral.triviagoservice.presentation.dto.CommentDto;
import com.austral.triviagoservice.presentation.dto.QuizDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class Responses {
    @Autowired
    CommentService commentService;
    @Autowired
    QuizService quizService;

    @Test
    public void commentResponses(){
        List<CommentDto> listComment =  commentService.findAllByQuizId(1L);
        assert(listComment.size() == 1);
        assert(listComment.get(0).getResponses().size() == 2);
    }
}
