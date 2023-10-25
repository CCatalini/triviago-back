package com.austral.triviagoservice.presentation.controller;

import com.austral.triviagoservice.business.QuizResolutionService;
import com.austral.triviagoservice.business.exception.InvalidContentException;
import com.austral.triviagoservice.business.exception.NotFoundException;
import com.austral.triviagoservice.persistence.domain.QuizResolution;
import com.austral.triviagoservice.presentation.dto.QuizResolutionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/quiz-resolution")
public class QuizResolutionController {
    QuizResolutionService quizResolutionService;

    public QuizResolutionController(QuizResolutionService quizResolutionService) {
        this.quizResolutionService = quizResolutionService;
    }


    @PostMapping
    public ResponseEntity<?> createQuizResolution(@RequestBody QuizResolutionDto quizResolutionDto) {
        try {
            QuizResolution quizResolution = quizResolutionService.createQuizResolution(quizResolutionDto);
            return ResponseEntity.ok(quizResolution);
        } catch(InvalidContentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
