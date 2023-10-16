package com.austral.triviagoservice.presentation.controller;

import com.austral.triviagoservice.business.QuizResolutionService;
import com.austral.triviagoservice.persistence.domain.QuizResolution;
import com.austral.triviagoservice.presentation.dto.QuizResolutionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/quiz-resolution")
public class QuizResolutionController {
    QuizResolutionService quizResolutionService;

    public QuizResolutionController(QuizResolutionService qrs) {
        this.quizResolutionService = qrs;
    }

    @PostMapping
    public ResponseEntity<QuizResolution> createQuizResolution(@RequestBody QuizResolutionDto quizResolutionDto) {
        QuizResolution quizResolution = quizResolutionService.createQuizResolution(quizResolutionDto);
        return ResponseEntity.ok(quizResolution);
    }

}
