package com.bitcamp.travelkkaebi.dto;

import com.bitcamp.travelkkaebi.regex.Regex;
import lombok.Getter;

import javax.validation.constraints.Pattern;

@Getter
public class UserUpdateDTO {
    private int userid;

    @Pattern(regexp = Regex.PHONE)
    private String email;

    @Pattern(regexp = Regex.PHONE)
    private String phone;

    @Pattern(regexp = Regex.PHONE)
    private String password;

    private String profileImageUrl;

    private String region;

    private String nickname;

}

