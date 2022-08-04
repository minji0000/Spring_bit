package com.kari.Board3.model;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Calendar;

@Data
public class BoardDTO {
    private int id;
    private int writerId;
    private String title;
    private String content;
    private String imageFileName;
    private Calendar writtenDate;
    private Calendar updatedDate;
    // 조회수
    private int views;
    // 추천수
    private int good;

    public void setWrittenDate(Timestamp writtenDate) {
        this.writtenDate = Calendar.getInstance();
        this.writtenDate.setTime(writtenDate);
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = Calendar.getInstance();
        this.updatedDate.setTime(updatedDate);
    }

    public boolean equals(Object o) {
        if(o instanceof BoardDTO) {
            BoardDTO b = (BoardDTO) o;
            return id == b.id;
        }
        return false;
    }


}
