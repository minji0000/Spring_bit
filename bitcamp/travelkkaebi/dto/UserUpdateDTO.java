package com.bitcamp.travelkkaebi.dto;

import lombok.Getter;

import javax.validation.constraints.Pattern;

import static com.bitcamp.travelkkaebi.regex.Regex.*;

@Getter
public class UserUpdateDTO {
    private int userid;

    @Pattern(regexp = EMAIL)
    private String email;

    @Pattern(regexp = PHONE)
    private String phone;

    @Pattern(regexp = PASSWORD)
    private String password;

    private String profileImageUrl;

    private String nickname;

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
}

