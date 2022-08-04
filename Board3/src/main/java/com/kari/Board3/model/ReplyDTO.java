package com.kari.Board3.model;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Calendar;

@Data
public class ReplyDTO {
    private int id;
    private int writerId;
    private int boardId;
    private String content;
    private Calendar writtenDate;
    private Calendar updatedDate;

    public void setWrittenDate(Timestamp writtenDate) {
        this.writtenDate = Calendar.getInstance();
        this.writtenDate.setTime(writtenDate);
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = Calendar.getInstance();
        this.updatedDate.setTime(updatedDate);
    }

    public boolean equals(Object o) {
        if(o instanceof ReplyDTO) {
            ReplyDTO r = (ReplyDTO) o;
            return id == r.id;
        }
        return false;
    }
}
