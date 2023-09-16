package com.austral.triviagoservice.business.impl;

import com.austral.triviagoservice.business.CommentService;
import com.austral.triviagoservice.business.exception.InvalidContentException;
import com.austral.triviagoservice.persistence.domain.Comment;
import com.austral.triviagoservice.persistence.domain.User;
import com.austral.triviagoservice.persistence.repository.CommentRepository;
import com.austral.triviagoservice.persistence.domain.Quiz;
import com.austral.triviagoservice.persistence.repository.CommentRepository;
import com.austral.triviagoservice.persistence.repository.QuizRepository;
import com.austral.triviagoservice.persistence.repository.UserRepository;
import com.austral.triviagoservice.presentation.dto.CommentDTO;
import com.austral.triviagoservice.presentation.dto.CommentResponseDTO;
import org.springframework.stereotype.Service;


import java.util.ArrayList;

import java.time.LocalDate;
import java.time.ZoneId;
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
        public CommentDTO create (Comment comment){
            comment.setLikes(0);
            comment.setCreationDate(LocalDate.now(ZoneId.of("America/Argentina/Buenos_Aires"))); //Buenos Aires time zone
            Comment com = commentRepository.save(comment);
            User user = userRepository.findById(com.getUserId()).orElse(null);
            assert user != null;
            return new CommentDTO(com.getId(), user.getEmail(), com.getContent(), com.getCreationDate(), com.getLikes(), null);
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
        public List<CommentDTO> findAllCommentsAndAnswersByQuiz(Long QuizId) {
            List<Comment> comments = findAllByQuizId(QuizId);
            List<CommentDTO> commentDTOS = new ArrayList<>();
            comments.forEach(
                    comment -> {
                        if (comment.getAnsweredCommentId() == null) {
                            commentDTOS.add(findCommentAndAnswers(comment.getId()));
                        }
                    }
            );
            return  commentDTOS;
        }

        @Override
        public CommentDTO findCommentAndAnswers(Long id) {
            Comment comment = findCommentById(id);
            List<Comment> answers = findAllByAnsweredCommentId(id);
            List<CommentResponseDTO> responses = new ArrayList<>();
            answers.forEach(
                    answer -> {
                        responses.add(
                                CommentResponseDTO.builder()
                                        .authorEmail(userRepository.findById(answer.getUserId()).orElse(null).getEmail())
                                        .content(answer.getContent())
                                        .creationDateTime(answer.getCreationDate())
                                        .likes(answer.getLikes())
                                        .build()
                        );
                    }
            );
            return CommentDTO.builder()
                    .id(comment.getId())
                    .authorEmail(userRepository.findById(comment.getUserId()).orElse(null).getEmail())
                    .content(comment.getContent())
                    .creationDate(comment.getCreationDate())
                    .responses(responses)
                    .build();
        }

        private List<Comment> findAllByAnsweredCommentId(Long answeredCommentId){
            return commentRepository.findAllByAnsweredCommentId(answeredCommentId);
        }

        private Comment findCommentById(Long id){
            return commentRepository.findById(id).orElse(null);
        }


        @Override
        public Comment findById(Long id) throws InvalidContentException {
            if(commentRepository.existsById(id)){
                return commentRepository.findById(id).get();
            }
            throw new InvalidContentException("Invalid content, Id does not exist");
        }

        @Override
        public void like(Long id, Boolean dislike) throws InvalidContentException {
            Comment comment = this.findById(id);
            if(dislike){comment.decrementLike();}
            else {comment.incrementLike();}
            commentRepository.save(comment);
        }
}
