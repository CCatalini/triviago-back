package com.austral.triviagoservice.presentation.controller;

import com.austral.triviagoservice.business.impl.LabelServiceImpl;
import com.austral.triviagoservice.persistence.domain.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private LabelServiceImpl labelService;

    @GetMapping()
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(labelService.findAll());

    }

}
