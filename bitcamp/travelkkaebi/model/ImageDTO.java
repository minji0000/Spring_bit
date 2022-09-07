package com.bitcamp.travelkkaebi.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class ImageDTO {
    private int imageId;
    private int categoryId;
    private int boardId;
    private int userId;
    private String imageUrl;
}