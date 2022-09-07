package com.bitcamp.travelkkaebi.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MannerDegreeDTO {
    private int mannerDegreeId;
    private int fromUserId;
    private int toUserId;
    private int degreeChange;
}
