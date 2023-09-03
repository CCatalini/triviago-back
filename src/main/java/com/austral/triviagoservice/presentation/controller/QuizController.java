package com.austral.triviagoservice.presentation.controller;

import com.austral.triviagoservice.business.exception.InvalidContentException;
import com.austral.triviagoservice.business.impl.CommentServiceImpl;
import com.austral.triviagoservice.business.impl.QuizServiceImpl;
import com.austral.triviagoservice.persistence.domain.Comment;
import com.austral.triviagoservice.persistence.domain.Quiz;
import com.austral.triviagoservice.presentation.dto.QuizCreate;
import com.austral.triviagoservice.presentation.dto.QuizFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    final
    QuizServiceImpl quizService;
    final CommentServiceImpl commentService;

    public QuizController(QuizServiceImpl quizService, CommentServiceImpl commentService){
        this.quizService = quizService;
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<?> getAllQuizzes(@RequestBody QuizFilter quizFilter,
                                        @RequestParam(value="page", defaultValue= "0" ) int page,
                                        @RequestParam(value="size", defaultValue="10") int size){
        Pageable pages = PageRequest.of(page, size);
        try {
            Page<Quiz> result = quizService.findAll(quizFilter, pages);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (InvalidContentException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Invalid filter values");
            return new ResponseEntity<>(response,HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PostMapping
    public ResponseEntity<?> createQuiz(@RequestBody Quiz quiz)  {
        return new ResponseEntity<>(quizService.createQuiz(quiz),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long quizId){
        try{
            QuizCreate dto = quizService.findById(quizId);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        catch (InvalidContentException e){
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{quizId}/comment")
    public ResponseEntity<List<Comment>> getComments(@PathVariable("quizId") long quizId) {
        try {
            if (quizService.findById(quizId) == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else {
                return new ResponseEntity<>(commentService.findAllByQuizId(quizId), HttpStatus.OK);
            }
        } catch (InvalidContentException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long quizId){
        try{
            Long value = quizService.deleteById(quizId);
            return new ResponseEntity<>(value, HttpStatus.OK);
        }
        catch (InvalidContentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/private/{invitationCode}")
    public ResponseEntity<?> getQuizByInvitationCode(@PathVariable("invitationCode") String invitationCode){
        try {
            QuizCreate dto = quizService.findByInvitationCode(invitationCode);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (InvalidContentException e){
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }

}
