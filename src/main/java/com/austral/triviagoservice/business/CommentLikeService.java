package com.austral.triviagoservice.business;

import com.austral.triviagoservice.business.exception.InvalidContentException;
import com.austral.triviagoservice.persistence.domain.CommentLike;

public interface CommentLikeService {

    CommentLike creat(CommentLike commentLike);
    CommentLike getById(Long id) throws InvalidContentException;
    void save(CommentLike commentLike);
}
