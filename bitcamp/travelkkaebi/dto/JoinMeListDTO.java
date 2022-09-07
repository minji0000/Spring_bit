package com.bitcamp.travelkkaebi.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class JoinMeListDTO {
    private int categoryId;
    private int joinMeId;
    private int userId;
    private int view;
    private int capacity;
    private int currentMemberCount;
    private int likeCount;

    private String nickname;
    private String title;
    private String content;
    private String region;
    private String profileImageUrl;

    private boolean closed;

    private Timestamp startDate;
    private Timestamp endDate;
    private Timestamp createTime;
}
