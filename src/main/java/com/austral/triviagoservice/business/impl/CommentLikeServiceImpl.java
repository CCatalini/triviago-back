package com.austral.triviagoservice.business.impl;

import com.austral.triviagoservice.business.CommentLikeService;
import com.austral.triviagoservice.persistence.domain.CommentLike;
import com.austral.triviagoservice.persistence.repository.CommentLikeRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentLikeServiceImpl implements CommentLikeService {

    private final CommentLikeRepository commentLikeRepository;

    public CommentLikeServiceImpl(CommentLikeRepository commentLikeRepository) {
        this.commentLikeRepository = commentLikeRepository;
    }

    @Override
    public void creat(CommentLike commentLike) {
        commentLikeRepository.save(commentLike);
    }
}
