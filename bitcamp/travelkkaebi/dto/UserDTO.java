package com.bitcamp.travelkkaebi.dto;

import com.bitcamp.travelkkaebi.encode.Password;
import com.bitcamp.travelkkaebi.entity.UserEntity;
import com.bitcamp.travelkkaebi.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

import static com.bitcamp.travelkkaebi.regex.Regex.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserDTO {

    private int id;

    @Pattern(regexp = USERNAME)
    private String username;

    @Pattern(regexp = PASSWORD)
    private String password;

    @Pattern(regexp = PASSWORD)
    private String secondPassword;

    private String nickname;
    private String profileImageUrl;

    @Pattern(regexp = EMAIL)
    private String email;

    @Pattern(regexp = PHONE)
    private String phone;
    private String name;
    private LocalDateTime blockedUntil = LocalDateTime.now();
    private int mannerDegree = 37;

    /**
     * userDTO -> userEntity
     */
    public static UserEntity toUserEntity(UserDTO userDTO) {
        return UserEntity.builder()
                .username(userDTO.getUsername())
                .password(Password.passwordEncoding(userDTO.getPassword()))
                .nickname(userDTO.getNickname())
                .blockedUntil(userDTO.getBlockedUntil())
                .profileImageUrl(userDTO.getProfileImageUrl())
                .email(userDTO.getEmail())
                .name(userDTO.getName())
                .phone(userDTO.getPhone())
                .role(UserRole.GENERAL)
                .mannerDegree(userDTO.getMannerDegree())
                .build();
    }

    /**
     * kakaoInfo -> userEntity
     */
    public static UserEntity kaKaoInfoToUserEntity(UserDTO userDTO) {
        return UserEntity.builder()
                .username("kakao_" + userDTO.getEmail())
                .password("kakao_password")
                .nickname(userDTO.getNickname())
                .blockedUntil(userDTO.getBlockedUntil())
                .profileImageUrl(userDTO.getProfileImageUrl())
                .email(userDTO.getEmail())
                .name("kakao_" + userDTO.getNickname())
                .phone("kakao_phone_number")
                .role(UserRole.GENERAL)
                .mannerDegree(userDTO.getMannerDegree())
                .build();
    }

    public UserDTO(String email, String profileImageUrl, String nickname) {
        this.email = email;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
    }
    

}
