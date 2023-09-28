package com.austral.triviagoservice.presentation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TotalLikesDto {
    private int totalLikes;

    private Boolean isLikeByUser;

    public TotalLikesDto(int totalLikes, Boolean isLikeByUser) {
        this.totalLikes = totalLikes;
        this.isLikeByUser = isLikeByUser;
    }
}
