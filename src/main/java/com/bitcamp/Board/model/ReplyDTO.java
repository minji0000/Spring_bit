package com.bitcamp.Board.model;

import lombok.Data;

@Data
public class ReplyDTO {
    private int id;
    private int boardId;
    private int writerId;
    private String content;

}
