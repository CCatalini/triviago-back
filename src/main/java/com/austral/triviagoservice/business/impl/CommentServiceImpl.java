package com.austral.triviagoservice.business.impl;

import com.austral.triviagoservice.business.CommentService;
import com.austral.triviagoservice.persistence.domain.Comment;
import com.austral.triviagoservice.persistence.repository.CommentRepository;
import com.austral.triviagoservice.persistence.domain.Quiz;
import com.austral.triviagoservice.persistence.repository.CommentRepository;
import com.austral.triviagoservice.persistence.repository.QuizRepository;
import com.austral.triviagoservice.persistence.repository.UserRepository;
import com.austral.triviagoservice.presentation.dto.CommentDTO;
import com.austral.triviagoservice.presentation.dto.CommentResponseDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository){
            this.commentRepository = commentRepository;
            this.userRepository = userRepository;
    }
        @Override
        public List<Comment> findAllByQuizId(Long quizId){
            return commentRepository.findByQuizId(quizId);
        }

        @Override
        public Comment create (Comment comment){
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

        @Override
        public CommentDTO findCommentAndAnswers(Long id){
            Comment comment = findCommentById(id);
            List<Comment> answers = findAllByAnsweredCommentId(id);
            List<CommentResponseDTO> responses = new ArrayList<>();
            answers.forEach(
                    answer -> {
                        responses.add(
                                CommentResponseDTO.builder()
                                        .authorEmail(userRepository.findById(answer.getUserId()).orElse(null).getEmail())
                                        .content(answer.getContent())
                                        .creationDateTime(answer.getCreationDateTime())
                                        .build()
                        );
                    }
            );
            return CommentDTO.builder()
                    .authorEmail(userRepository.findById(comment.getUserId()).orElse(null).getEmail())
                    .content(comment.getContent())
                    .creationDateTime(comment.getCreationDateTime())
                    .responses(responses)
                    .build();
        }

        private List<Comment> findAllByAnsweredCommentId(Long answeredCommentId){
            return commentRepository.findAllByAnsweredCommentId(answeredCommentId);
        }

        private Comment findCommentById(Long id){
            return commentRepository.findById(id).orElse(null);
        }


}
