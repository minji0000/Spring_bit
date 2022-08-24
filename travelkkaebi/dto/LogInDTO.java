package com.bitcamp.travelkkaebi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogInDTO {
    private int id;
    private String username;
    private String password;
    private String token;
}
