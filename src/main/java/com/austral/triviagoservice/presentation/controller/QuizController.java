package com.austral.triviagoservice.presentation.controller;

import com.austral.triviagoservice.business.exception.InvalidContentException;
import com.austral.triviagoservice.business.impl.QuizServiceImpl;
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
import java.util.Map;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    final
    QuizServiceImpl quizService;

    public QuizController(QuizServiceImpl quizService) {
        this.quizService = quizService;
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
    public ResponseEntity<?> findById(@PathVariable("id") long quizId){
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


}
