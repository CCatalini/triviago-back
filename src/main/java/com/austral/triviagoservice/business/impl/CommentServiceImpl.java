package com.austral.triviagoservice.business.impl;

import com.austral.triviagoservice.business.CommentService;
import com.austral.triviagoservice.persistence.domain.Comment;
<<<<<<< Updated upstream
import com.austral.triviagoservice.persistence.repository.CommentRepository;
=======
import com.austral.triviagoservice.persistence.domain.Quiz;
import com.austral.triviagoservice.persistence.repository.CommentRepository;
import com.austral.triviagoservice.persistence.repository.QuizRepository;
>>>>>>> Stashed changes
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
<<<<<<< Updated upstream

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();

=======
    private final QuizRepository quizRepository;

    public CommentServiceImpl(CommentRepository commentRepository,
                              QuizRepository quizRepository) {
        this.commentRepository = commentRepository;
        this.quizRepository = quizRepository;
    }

    @Override
    public List<Comment> findAllByQuizId(Quiz quiz) {
        Long quizId = quiz.getId();
        return commentRepository.findByQuizId(quizId);
>>>>>>> Stashed changes
    }

    @Override
    public Comment create(Comment comment) {
        commentRepository.save(comment);
        return comment;
    }

    @Override
    public Comment editComment(Comment comment, String newComment) {
        commentRepository.findById(comment.getId()).ifPresent(comment1 -> {
            comment1.setContent(newComment);
            commentRepository.save(comment1);
        });
        return comment;
    }

    @Override
    public Comment deleteComment(Comment comment) {
        commentRepository.delete(comment);
        return comment;
    }
<<<<<<< Updated upstream
}
=======
}
>>>>>>> Stashed changes
