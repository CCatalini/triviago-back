package com.austral.triviagoservice.presentation.controller;

import com.austral.triviagoservice.business.impl.LabelServiceImpl;
import com.austral.triviagoservice.presentation.dto.LabelCreateDto;
import com.austral.triviagoservice.presentation.dto.LabelDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/label")
public class LabelController {

    private final LabelServiceImpl labelService;

    public LabelController(LabelServiceImpl labelService) {
        this.labelService = labelService;
    }

    @GetMapping()
    public ResponseEntity<List<LabelDto>> findAll(){
        return ResponseEntity.ok(labelService.findAll());

    }
    @PostMapping()
    public ResponseEntity<?> save(@RequestBody LabelCreateDto labelDto){
        try {
            return new ResponseEntity<>(labelService.save(labelDto), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
