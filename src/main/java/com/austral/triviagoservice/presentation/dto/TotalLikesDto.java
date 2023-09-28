package com.austral.triviagoservice.presentation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TotalLikesDto {
    private int totalLikes;

    public TotalLikesDto(int totalLikes) {
        this.totalLikes = totalLikes;
    }
}
