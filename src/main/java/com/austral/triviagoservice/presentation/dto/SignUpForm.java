package com.austral.triviagoservice.presentation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpForm {

        private String email;
        private String password;
        private String firstName;
        private String lastName;
        private String birthDate;


        public SignUpForm() {
        }


}
