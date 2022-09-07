package com.bitcamp.travelkkaebi.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UpdateMemberCountDTO {
    private int userId;
    private int joinMeId;
    private int currentMemberCount;
}
