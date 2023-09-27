package com.austral.triviagoservice.business.impl;

import com.austral.triviagoservice.business.CommentService;
import com.austral.triviagoservice.business.exception.InvalidContentException;
import com.austral.triviagoservice.business.exception.NotFoundException;
import com.austral.triviagoservice.persistence.domain.Comment;
import com.austral.triviagoservice.persistence.domain.CommentLike;
import com.austral.triviagoservice.persistence.domain.Quiz;
import com.austral.triviagoservice.persistence.domain.User;
import com.austral.triviagoservice.persistence.repository.CommentRepository;
import com.austral.triviagoservice.persistence.repository.QuizRepository;
import com.austral.triviagoservice.persistence.repository.UserRepository;
import com.austral.triviagoservice.presentation.dto.AuthorDto;
import com.austral.triviagoservice.presentation.dto.CommentDto;
import com.austral.triviagoservice.presentation.dto.CommentCreateDto;
import lombok.SneakyThrows;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final QuizRepository quizRepository;
    private final CommentLikeServiceImpl commentLikeService;

    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository,
                              QuizRepository quizRepository, CommentLikeServiceImpl commentLikeService) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.quizRepository = quizRepository;
        this.commentLikeService = commentLikeService;
    }


    @Override
    public Comment create(CommentCreateDto commentDto) throws NotFoundException {
        // Check that related quiz exists
        if (!quizRepository.existsById(commentDto.getQuizId()))
            throw new NotFoundException("Quiz with id " + commentDto.getQuizId() + " not found");

        // Create and save comment
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Quiz> quiz = quizRepository.findById(commentDto.getQuizId());
        if (quiz.isEmpty()) throw new NotFoundException("Not found quiz");
        commentDto.setCreationDate(LocalDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires")));
        commentDto.setLikes(new ArrayList<>());
        Comment comment = new Comment(commentDto, user.getId());
        commentRepository.save(comment);

        // Update parent comment (if necessary)
        if (commentDto.getParentCommentId() != null) {
            Comment parentComment = findCommentById(commentDto.getParentCommentId());
            parentComment.getReplies().add(comment);
            commentRepository.save(parentComment);
        }

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

    @Override
    public List<CommentDto> findAllByQuizId(Long quizId) {
        return commentRepository.findAllByQuizId(quizId)
                .stream()
                .filter(c -> c.getParentCommentId() == null)
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @SneakyThrows
    private CommentDto entityToDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .author(getAuthor(comment.getUserId()))
                .content(comment.getContent())
                .creationDate(comment.getCreationDateTime().toString())
                .responses(comment.getReplies().stream().map(this::entityToDto).collect(Collectors.toList()))
                .parentCommentId(comment.getParentCommentId())
                .build();
    }

    private AuthorDto getAuthor (Long userId) throws NotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User with id: " + userId + " not found!"));
        return new AuthorDto(user);
    }

    private Comment findCommentById(Long id) throws NotFoundException {
        return commentRepository.findById(id).orElseThrow(() -> new NotFoundException("Comment with id: " + id + " not found!"));
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
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();//gets actual user in session
        comment.getLikes().stream()
                .filter(like -> like.getUser().getId().equals(user.getId())) //filters likes by userId
                .findFirst()
                .ifPresentOrElse(
                        like -> { //if optional has value
                            if (like.getIsLike() == dislike) { //valid case
                                comment.quitLike(like); //quits actual from structure
                                user.quitLike(like);
                                like.setIsLike(!dislike);
                                commentLikeService.create(like); //writes into database
                                comment.setLike(like);//writes into Comment entity
                                user.setLike(like);
                            }
                            //Else is an invalid
                        },
                        () -> { //If optional has no value
                            CommentLike value = commentLikeService.create(new CommentLike(user, comment, !dislike));
                            comment.setLike(value);
                            user.setLike(value);
                        });
    }


    @Override
    public void removeLike(Long id) throws InvalidContentException {
        Comment comment = this.findById(id);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();//gets actual user in session
        comment.getLikes().stream().filter(
                        like -> like.getUser().getId().equals(user.getId()))
                .findFirst()
                .ifPresent(
                        like -> {
                            user.quitLike(like);
                            comment.quitLike(like); //removes the like
                            commentLikeService.delete(like); //delets from database
                            commentRepository.save(comment); //saves comment
                        });
    }
}