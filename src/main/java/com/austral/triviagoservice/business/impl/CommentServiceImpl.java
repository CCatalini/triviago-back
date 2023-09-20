package com.austral.triviagoservice.business.impl;

import com.austral.triviagoservice.business.CommentService;
import com.austral.triviagoservice.business.exception.InvalidContentException;
import com.austral.triviagoservice.business.exception.NotFoundException;
import com.austral.triviagoservice.persistence.domain.Comment;
import com.austral.triviagoservice.persistence.domain.Quiz;
import com.austral.triviagoservice.persistence.domain.User;
import com.austral.triviagoservice.persistence.repository.CommentRepository;
import com.austral.triviagoservice.persistence.repository.QuizRepository;
import com.austral.triviagoservice.persistence.repository.UserRepository;
import com.austral.triviagoservice.presentation.dto.AuthorDto;
import com.austral.triviagoservice.presentation.dto.CommentDTO;
import com.austral.triviagoservice.presentation.dto.CommentResponseDTO;
import com.austral.triviagoservice.persistence.repository.UserRepository;
import com.austral.triviagoservice.presentation.dto.CommentCreateDto;
import org.springframework.stereotype.Service;


import java.util.ArrayList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    private final QuizRepository quizRepository;

    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository,
                              QuizRepository quizRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.quizRepository = quizRepository;
    }

    @Override
    public List<Comment> findAllByQuizId(Long quizId) {
        return commentRepository.findByQuizId(quizId);
    }

    @Override
    public CommentDTO create(CommentCreateDto commentDto) throws NotFoundException {
        Optional<User> user = userRepository.findById(commentDto.getUserId());
        if (user.isEmpty()) throw new NotFoundException("Not found user");
        Optional<Quiz> quiz = quizRepository.findById(commentDto.getQuizId());
        if (quiz.isEmpty()) throw new NotFoundException("Not found quiz");
        commentDto.setCreationDate(LocalDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires")));
        commentDto.setLikes(0);
        Comment comment = new Comment(commentDto);
        Comment aux = commentRepository.save(comment);

        return CommentDTO.builder()
                .id(aux.getId())
                .author(getAuthor(aux.getUserId()))
                .content(aux.getContent())
                .creationDate(aux.getCreationDateTime().toString())
                .responses(List.of())
                .build();
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
                                    .author(getAuthor(answer.getUserId()))
                                    .content(answer.getContent())
                                    .creationDateTime(answer.getCreationDateTime().toString())
                                    .likes(answer.getLikes())
                                    .build()
                    );
                }
        );
        return CommentDTO.builder()
                .id(comment.getId())
                .author(getAuthor(comment.getUserId()))
                .content(comment.getContent())
                .creationDate(comment.getCreationDateTime().toString())
                .responses(responses)
                .build();
    }

    private AuthorDto getAuthor (Long userId){
        User user = userRepository.findById(userId).orElse(null);
        assert user != null;
        return AuthorDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
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
        if (dislike) {
            comment.decrementLike();
        } else {
            comment.incrementLike();
        }
        commentRepository.save(comment);
    }
}
