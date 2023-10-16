package com.austral.triviagoservice.presentation.dto;

import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class UserInfoRequest {

    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    List<Long> createdQuizzes;
    List<Long> savedQuizes;
    List<Long> following;
    private LocalDate createdDate;

}
