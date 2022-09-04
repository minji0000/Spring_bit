package com.bitcamp.travelkkaebi.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PageAndWordDTO {
    private int startNum;
    private int pageSize;
    private String word;
}
