package com.austral.triviagoservice.business.impl;

import com.austral.triviagoservice.business.CommentLikeService;
import com.austral.triviagoservice.business.exception.InvalidContentException;
import com.austral.triviagoservice.business.exception.NotFoundException;
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
    public CommentLike create(CommentLike commentLike) {
        return commentLikeRepository.save(commentLike);
    }

    @Override
    public CommentLike getById(Long id) throws NotFoundException {
        return commentLikeRepository.findById(id).orElseThrow(() -> new NotFoundException("Comment like with id: " + id + " not found!"));
    }

    @Override
    public void delete(CommentLike commentLike){
        commentLikeRepository.delete(commentLike);
    }
}
