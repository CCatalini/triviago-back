package com.austral.triviagoservice.business;

import com.austral.triviagoservice.business.exception.InvalidContentException;
import com.austral.triviagoservice.business.exception.NotFoundException;
import com.austral.triviagoservice.persistence.domain.CommentLike;

public interface CommentLikeService {

    CommentLike create(CommentLike commentLike);
    CommentLike getById(Long id) throws InvalidContentException, NotFoundException;
    void delete(CommentLike commentLike);
}
