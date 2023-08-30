package com.austral.triviagoservice.presentation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequest {

    private String username;
    private String password;

    public AuthenticationRequest(){}


}
