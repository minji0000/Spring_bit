package com.bitcamp.travelkkaebi.dto;

import com.bitcamp.travelkkaebi.model.LikeOrDislikeDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikeOrDislikeResponseDTO extends LikeOrDislikeDTO {
    private int likeCount;
    private int dislikeCount;
}
