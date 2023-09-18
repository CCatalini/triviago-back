package com.austral.triviagoservice.business.impl;

import com.austral.triviagoservice.business.CommentLikeService;
import com.austral.triviagoservice.business.exception.InvalidContentException;
import com.austral.triviagoservice.persistence.domain.CommentLike;
import com.austral.triviagoservice.persistence.repository.CommentLikeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentLikeServiceImpl implements CommentLikeService {

    private final CommentLikeRepository commentLikeRepository;

    public CommentLikeServiceImpl(CommentLikeRepository commentLikeRepository) {
        this.commentLikeRepository = commentLikeRepository;
    }

    @Override
    public CommentLike creat(CommentLike commentLike) {
        return commentLikeRepository.save(commentLike);
    }

    @Override
    public CommentLike getById(Long id) throws InvalidContentException {
        Optional<CommentLike> optional = commentLikeRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        throw new InvalidContentException("Invalid like Id");
    }

    @Override
    public void save(CommentLike commentLike) {
        commentLikeRepository.save(commentLike);
    }
}
