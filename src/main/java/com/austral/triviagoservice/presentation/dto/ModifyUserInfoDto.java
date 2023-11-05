package com.austral.triviagoservice.presentation.dto;

import lombok.Getter;

import java.time.LocalDate;
@Getter
public class ModifyUserInfoDto {

    private String firstName;
    private String lastName;
    private LocalDate birthDate;
}
