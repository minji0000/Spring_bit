package com.bitcamp.travelkkaebi.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class LikeOrDislikeDTO {
    private int likeOrDislikeId;
    private int categoryId;
    private int boardId;
    private int userId;
    private boolean liked;
    private boolean disliked;
}