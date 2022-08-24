package com.bitcamp.travelkkaebi.model;

import lombok.*;

import java.util.Calendar;

@Getter
@Setter
public class LikeOrDislikeDTO {
    private int likeOrDislikeId;
    private int categoryId;
    private int boardId;
    private int userId;
    private boolean liked;
    private boolean disliked;
    private Calendar createTime;
    private Calendar updateTime;
}