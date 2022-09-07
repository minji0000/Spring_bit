package com.bitcamp.travelkkaebi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;

@Getter
@Setter
public class ReviewReplyResponseDTO {

    private int ReviewReplyId;
    private int categoryId;
    private int boardId;
    private int userId;
    private String nickname;
    private String profileImageUrl;
    private String comment;
    private Calendar createTime;
    private Calendar updateTime;


}
