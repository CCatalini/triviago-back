package com.austral.triviagoservice.business;

import com.austral.triviagoservice.persistence.domain.User;
import com.austral.triviagoservice.presentation.dto.AuthorDto;

public interface UserService {

    public void saveUser(User user);

    User findByEmail(String username);

    AuthorDto findAuthorById(Long id);
}
