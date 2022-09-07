package com.bitcamp.travelkkaebi.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
public class ReviewResponseDTO {

    private int reviewId;
    private int categoryId;
    private int userId;
    private String nickname;
    private String profileImageUrl;
    private int mannerDegree;
    private String title;
    private String content;
    private String region;
    private int view;
    private Timestamp createTime;
    private Timestamp updateTime;

}
