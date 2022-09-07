package com.bitcamp.travelkkaebi.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ImageAndCommentDTO {

    private int imageAndCommentId;
    private int categoryId;
    private int boardId;
    private int userId;
    private String imageUrl;
    private String comment;
    private Timestamp createTime;
    private Timestamp updateTime;

}
