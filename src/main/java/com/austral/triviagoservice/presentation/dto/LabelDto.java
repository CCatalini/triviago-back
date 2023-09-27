package com.austral.triviagoservice.presentation.dto;

import com.austral.triviagoservice.persistence.domain.Label;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LabelDto {
    private String value;

    public LabelDto(Label label) {
        this.value = label.getValue();
    }

}
