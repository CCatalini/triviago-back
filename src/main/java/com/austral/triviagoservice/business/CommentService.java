package com.austral.triviagoservice.business;


import com.austral.triviagoservice.persistence.domain.Comment;
<<<<<<< Updated upstream
=======
import com.austral.triviagoservice.persistence.domain.Quiz;
>>>>>>> Stashed changes

import java.util.List;

public interface CommentService {
<<<<<<< Updated upstream
    List<Comment> findAll();
    Comment create(Comment comment);
    Comment editComment(Comment comment, String newContent);
    Comment deleteComment(Comment comment);
}
=======
    List<Comment> findAllByQuizId(Quiz quiz);
    Comment create(Comment comment);
    Comment editComment(Comment comment, String newContent);
    Comment deleteComment(Comment comment);
}
>>>>>>> Stashed changes
