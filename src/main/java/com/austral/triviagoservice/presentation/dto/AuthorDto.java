package com.austral.triviagoservice.presentation.dto;

import com.austral.triviagoservice.persistence.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthorDto {
    private String firstName;
    private String lastName;
    private String email;

    public AuthorDto(User author) {
        this.firstName = author.getFirstName();
        this.lastName = author.getLastName();
        this.email = author.getEmail();
    }
}
