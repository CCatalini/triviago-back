package com.austral.triviagoservice.presentation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class AuthorDto {
    private String firstName;
    private String lastName;
    private String email;



}
