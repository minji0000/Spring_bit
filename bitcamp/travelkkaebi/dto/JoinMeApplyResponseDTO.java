package com.bitcamp.travelkkaebi.dto;

import com.bitcamp.travelkkaebi.model.JoinMeApplyDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinMeApplyResponseDTO extends JoinMeApplyDTO {
    private String nickname;
}