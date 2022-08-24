package com.bitcamp.travelkkaebi.dto;

import com.bitcamp.travelkkaebi.encode.Password;
import com.bitcamp.travelkkaebi.entity.Gender;
import com.bitcamp.travelkkaebi.entity.UserEntity;
import com.bitcamp.travelkkaebi.entity.UserRole;
import com.bitcamp.travelkkaebi.regex.Regex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserDTO {
    private static final String BASIC_IMAGE_URL = "basicImageUrl";

    @Pattern(regexp = Regex.USERNAME)
    private String username;

    @Pattern(regexp = Regex.PASSWORD)
    private String password;
    private String nickname;
    private String profileImageUrl;

    @Pattern(regexp = Regex.EMAIL)
    private String email;

    @Pattern(regexp = Regex.PHONE)
    private String phone;
    private Gender sex;
    private String name;
    private String region;

    private void setProfileImageUrl() {
        this.profileImageUrl = UserDTO.BASIC_IMAGE_URL;
    }

    /**
     * userDTO -> userEntity
     */

    public static UserEntity toUserEntity(UserDTO userDTO) {
        defaultImageUrl(userDTO);
        return UserEntity.builder()
                .username(userDTO.getUsername())
                .password(Password.passwordEncoding(userDTO.getPassword()))
                .nickname(userDTO.getNickname())
                .profileImageUrl(userDTO.getProfileImageUrl())
                .email(userDTO.getEmail())
                .name(userDTO.getName())
                .phone(userDTO.getPhone())
                .gender(userDTO.getSex())
                .region(userDTO.getRegion())
                .role(UserRole.GENERAL)
                .build();
    }

    private static void defaultImageUrl(UserDTO userDTO) {
        if (userDTO.getProfileImageUrl() == null) {
            userDTO.setProfileImageUrl();
        }
    }

}
