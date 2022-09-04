package com.bitcamp.travelkkaebi.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class JoinMeOneDTO {
    private int joinMeId;
    private int userId;
    private int categoryId;
    private int view;
    private int charge;
    private int capacity;
    private int currentMemberCount;
    private int mannerDegree;

    private String nickname;
    private String title;
    private String content;
    private String region;

    private boolean closed;

    private Timestamp startDate;
    private Timestamp endDate;
    private Timestamp createTime;
    private Timestamp updateTime;
}
