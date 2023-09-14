package com.austral.triviagoservice.presentation.controller;

import com.austral.triviagoservice.business.exception.InvalidContentException;
import com.austral.triviagoservice.business.impl.CommentServiceImpl;
import com.austral.triviagoservice.persistence.domain.Comment;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentServiceImpl commentService;
    private final ObjectMapper objectMapper;
    public CommentController(CommentServiceImpl commentService, ObjectMapper objectMapper) {
        this.commentService = commentService;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody Comment comment){
        return new ResponseEntity<>(commentService.create(comment), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCommentById(@PathVariable("id") Long id){
        try{
            Comment comment = commentService.findById(id);
            return new ResponseEntity<>(comment, HttpStatus.OK);
        }
        catch (InvalidContentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<?> likeComment(@PathVariable("id") Long id, @RequestParam("token") String token){
        try{
            commentService.like(id, false, token);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(InvalidContentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/dislike")
    public ResponseEntity<?> dislikeComment(@PathVariable("id") Long id, @RequestParam("token") String token){
        try{
            commentService.like(id, true, token);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (InvalidContentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editContent(@PathVariable("id") Long id, @RequestBody JsonNode jsonNode){
        try{
            commentService.editContent(id, jsonNode.get("content").asText());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (InvalidContentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
