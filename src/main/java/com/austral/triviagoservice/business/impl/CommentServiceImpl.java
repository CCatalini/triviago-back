package com.austral.triviagoservice.business.impl;

import com.austral.triviagoservice.business.CommentService;
import com.austral.triviagoservice.business.exception.InvalidContentException;
import com.austral.triviagoservice.business.exception.NotFoundException;
import com.austral.triviagoservice.business.helper.ValidateUser;
import com.austral.triviagoservice.persistence.domain.Comment;
import com.austral.triviagoservice.persistence.domain.CommentLike;
import com.austral.triviagoservice.persistence.domain.Quiz;
import com.austral.triviagoservice.persistence.domain.User;
import com.austral.triviagoservice.persistence.repository.CommentRepository;
import com.austral.triviagoservice.persistence.repository.QuizRepository;
import com.austral.triviagoservice.presentation.dto.EditedContent;
import com.austral.triviagoservice.persistence.repository.UserRepository;
import com.austral.triviagoservice.presentation.dto.CommentCreateDto;
import com.austral.triviagoservice.security.TokenDecode;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public List<Comment> findAllByQuizId(Long quizId) {
        return commentRepository.findByQuizId(quizId);
    }

    @Override
    public Comment create(CommentCreateDto commentDto) throws NotFoundException {
        Optional<User> user = userRepository.findById(commentDto.getUserId());
        if (user.isEmpty()) throw new NotFoundException("Not found user");
        Optional<Quiz> quiz = quizRepository.findById(commentDto.getQuizId());
        if (quiz.isEmpty()) throw new NotFoundException("Not found quiz");
        commentDto.setCreationDate(LocalDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires")));
        commentDto.setLikes(new ArrayList<>());
        Comment comment = new Comment(commentDto);
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

    @Override
    public Comment findById(Long id) throws InvalidContentException {
        if (commentRepository.existsById(id)) {
            return commentRepository.findById(id).get();
        }
        throw new InvalidContentException("Invalid content, Id does not exist");
    }


        @Override
        public void like(Long id, Boolean dislike) throws InvalidContentException {
            Comment comment = this.findById(id);

            CommentLike like = new CommentLike(userId, id, !dislike);
            Boolean existsL = comment.hasLike(userId);
            if(existsL){//Already liked
                CommentLike actual = comment.findLike(userId);
                Boolean actualStatus = actual.getIsLike();
                if(actualStatus && !dislike){ //Invalid condition, can´t like an already liked comment
                    throw new InvalidContentException("Invalid like petition: dislike =" + actualStatus.toString());
                }
                else{//Valid petition, like o dislike a comment that has been disliked or liked
                    comment.quitLike(actual); //quits actual from structure
                    actual.setIsLike(like.getIsLike());//inverts status
                    commentLikeService.create(actual); //writes into database
                    comment.setLike(actual);//writes into Comment entity
                }
            }
            else{ //First like case
                CommentLike newLike = commentLikeService.create(like);
                comment.setLike(newLike);
            }
            commentRepository.save(comment); //saves changes
        }

        @Override
        public void editContent(Long id, EditedContent content) throws InvalidContentException{
           Comment comment = this.findById(id);
           ValidateUser.validate(comment.getUserId(), content.getToken());
           comment.setContent(content.getNewContent());
           commentRepository.save(comment);
        }

}