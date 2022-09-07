package com.bitcamp.travelkkaebi.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TravelMapDTO {
    private int travelMapId;
    private int categoryId;
    private int boardId;
    private int userId;
    private String mapUrl;
}