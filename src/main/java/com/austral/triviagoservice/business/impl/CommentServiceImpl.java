package com.austral.triviagoservice.business.impl;

import com.austral.triviagoservice.business.CommentService;
import com.austral.triviagoservice.business.exception.InvalidContentException;
import com.austral.triviagoservice.business.exception.NotFoundException;
import com.austral.triviagoservice.business.exception.UnauthorizedException;
import com.austral.triviagoservice.persistence.domain.Comment;
import com.austral.triviagoservice.persistence.domain.CommentLike;
import com.austral.triviagoservice.persistence.domain.Quiz;
import com.austral.triviagoservice.persistence.domain.User;
import com.austral.triviagoservice.persistence.repository.CommentRepository;
import com.austral.triviagoservice.persistence.repository.QuizRepository;
import com.austral.triviagoservice.persistence.repository.UserRepository;
import com.austral.triviagoservice.presentation.dto.*;
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
    public CommentDto create(CommentCreateDto commentDto) throws NotFoundException {
        // Check that related quiz exists
        if (!quizRepository.existsById(commentDto.getQuizId()))
            throw new NotFoundException("Quiz with id " + commentDto.getQuizId() + " not found");

        // Create and save comment
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Comment comment = new Comment(commentDto, user.getId());
        commentRepository.save(comment);

        // Update parent comment (if necessary)
        if (commentDto.getParentCommentId() != null) {
            Comment parentComment = findCommentById(commentDto.getParentCommentId());
            parentComment.getReplies().add(comment);
            commentRepository.save(parentComment);
        }

        return entityToDto(comment);
    }
    @Override
    public CommentDto editComment(Long id, CommentEditDto commentDto) throws NotFoundException, UnauthorizedException {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isEmpty()) throw new NotFoundException("Comment with id " + id + " not found");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!comment.get().getUserId().equals(user.getId())) throw new UnauthorizedException("You are not authorized to edit this comment");
        comment.get().setContent(commentDto.getNewContent());
        return entityToDto(commentRepository.save(comment.get()));
    }

    @Override
    public void deleteComment(Long id) throws NotFoundException, UnauthorizedException {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isEmpty()) throw new NotFoundException("Comment with id " + id + " not found");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!comment.get().getUserId().equals(user.getId())) throw new UnauthorizedException("You are not authorized to delete this comment");
        commentRepository.deleteById(id);
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
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CommentDto commentDto = CommentDto.builder()
                .id(comment.getId())
                .author(getAuthor(comment.getUserId()))
                .content(comment.getContent())
                .creationDate(comment.getCreationDateTime().toString())
                .responses(comment.getReplies().stream().map(this::entityToDto).collect(Collectors.toList()))
                .parentCommentId(comment.getParentCommentId())
                .likes(comment.getLikes().stream().mapToInt(like -> like.getIsLike() ? 1 : -1).sum())
                .build();
        Optional<CommentLike> optionalCommentLike = comment.getLikes().stream().filter(like -> like.getUser().getId().equals(user.getId())).findFirst();
        optionalCommentLike.ifPresent(commentLike -> commentDto.setIsLikedByUser(commentLike.getIsLike()));
        return commentDto;
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
    public TotalLikesDto like(Long id, Boolean dislike) throws InvalidContentException {
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
                            } else {
                                comment.quitLike(like); //quits actual from structure
                                user.quitLike(like);
                                commentLikeService.delete(like);
                            }
                            //Else is an invalid
                        },
                        () -> { //If optional has no value
                            CommentLike value = commentLikeService.create(new CommentLike(user, comment, !dislike));
                            comment.setLike(value);
                            user.setLike(value);
                        });
        return new TotalLikesDto(comment.getLikes().stream().mapToInt(like -> like.getIsLike() ? 1 : -1).sum());
    }


    @Override
    public TotalLikesDto removeLike(Long id) throws InvalidContentException {
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
                        });
        return new TotalLikesDto(comment.getLikes().stream().mapToInt(like -> like.getIsLike() ? 1 : -1).sum());
    }
}