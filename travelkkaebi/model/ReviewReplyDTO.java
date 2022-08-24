package com.bitcamp.travelkkaebi.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Calendar;

@Getter
@Setter
public class ReviewReplyDTO {

    private int ReviewReplyId;
    private int categoryId;
    private int boardId;
    private int writerId;
    private String comment;
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
