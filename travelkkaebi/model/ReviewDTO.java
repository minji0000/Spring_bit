package com.bitcamp.travelkkaebi.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Calendar;

@Getter
@Setter
public class ReviewDTO {

    private int reviewId;
    private int categoryId;
    private int writerId;
    private String title;
    private String content;
    private String region;
    private int likeCount;
    private int dislikeCount;
    private int view;
    private Calendar createTime;
    private Calendar updateTime;

    public void setCreateTime(Timestamp createTime) {
        this.createTime = Calendar.getInstance();
        this.createTime.setTime(createTime);
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = Calendar.getInstance();
        this.updateTime.setTime(updateTime);
    }
}
