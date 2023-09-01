package com.austral.triviagoservice.presentation.controller;

import com.austral.triviagoservice.business.exception.InvalidContentException;
import com.austral.triviagoservice.business.impl.CommentServiceImpl;
import com.austral.triviagoservice.persistence.domain.Comment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentServiceImpl commentService;

    public CommentController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody Comment comment){
        return new ResponseEntity<>(commentService.create(comment), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCommentById(@RequestParam("id") Long id){
        try{
            Comment comment = commentService.findById(id);
            return new ResponseEntity<>(comment, HttpStatus.OK);
        }
        catch (InvalidContentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<?> likeComment(@RequestParam("id") Long id){
        try{
            commentService.like(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(InvalidContentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
