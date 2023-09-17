package com.austral.triviagoservice.presentation.controller;

import com.austral.triviagoservice.business.exception.InvalidContentException;

import com.austral.triviagoservice.business.exception.NotFoundException;
import com.austral.triviagoservice.business.impl.CommentServiceImpl;
import com.austral.triviagoservice.persistence.domain.Comment;
import com.austral.triviagoservice.presentation.dto.EditedContent;
import com.austral.triviagoservice.presentation.dto.CommentCreateDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentServiceImpl commentService;

    public CommentController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody CommentCreateDto commentDto) {
        try {
            Comment comment = commentService.create(commentDto);
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCommentById(@PathVariable("id") Long id) {
        try {
            Comment comment = commentService.findById(id);
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } catch (InvalidContentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<?> likeComment(@PathVariable("id") Long id){
        try{
            commentService.like(id, false);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (InvalidContentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/dislike")
    public ResponseEntity<?> dislikeComment(@PathVariable("id") Long id){
        try{
            commentService.like(id, true);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (InvalidContentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editContent(@PathVariable("id") Long id, @ModelAttribute EditedContent editedContent){
        try{
            commentService.editContent(id, editedContent);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (InvalidContentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
