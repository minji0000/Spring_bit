package com.bitcamp.travelkkaebi.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class JoinMeApplyDTO {
    private int joinMeApplyId;
    private int joinMeId;
    private int userId;
    private String comment;
    private boolean picked;

    private Timestamp createTime;
    private Timestamp updateTime;
}
