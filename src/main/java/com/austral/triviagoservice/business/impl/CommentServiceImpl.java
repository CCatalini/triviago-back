package com.austral.triviagoservice.business.impl;

import com.austral.triviagoservice.business.CommentService;
import com.austral.triviagoservice.business.exception.InvalidContentException;
import com.austral.triviagoservice.persistence.domain.Comment;
import com.austral.triviagoservice.persistence.repository.CommentRepository;
import com.austral.triviagoservice.persistence.domain.Quiz;
import com.austral.triviagoservice.persistence.repository.CommentRepository;
import com.austral.triviagoservice.persistence.repository.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
            this.commentRepository = commentRepository;
    }
        @Override
        public List<Comment> findAllByQuizId(Long quizId){
            return commentRepository.findByQuizId(quizId);
        }

        @Override
        public Comment create (Comment comment){
            comment.setLikes(0);
            commentRepository.save(comment);
            return comment;
        }

        @Override
        public Comment editComment (Comment comment, String newComment){
            commentRepository.findById(comment.getId()).ifPresent(comment1 -> {
                comment1.setContent(newComment);
                commentRepository.save(comment1);
            });
            return comment;
        }

        @Override
        public Comment deleteComment (Comment comment){
            commentRepository.delete(comment);
            return comment;
        }

        public Comment findById(Long id) throws InvalidContentException {
            if(commentRepository.existsById(id)){
                return commentRepository.findById(id).get();
            }
            throw new InvalidContentException("Invalid content, Id does not exist");
        }

        public void like(Long id, Boolean dislike) throws InvalidContentException {
            Comment comment = this.findById(id);
            if(dislike){comment.decrementLike();}
            else {comment.incrementLike();}
            commentRepository.save(comment);
        }
}
