package com.austral.triviagoservice.presentation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TotalLikesDto {
    private int totalLikes;

    private Boolean isLikedByUser;

    public TotalLikesDto(int totalLikes, Boolean isLikedByUser) {
        this.totalLikes = totalLikes;
        this.isLikedByUser = isLikedByUser;
    }
}
