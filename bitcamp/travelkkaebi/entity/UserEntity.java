package com.bitcamp.travelkkaebi.entity;

import com.bitcamp.travelkkaebi.dto.UserUpdateDTO;
import com.bitcamp.travelkkaebi.encode.Password;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Column(name = "blocked_until", nullable = false)
    private LocalDateTime blockedUntil;

    @Column(name = "manner_degree", nullable = false)
    private int mannerDegree;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean blocked = false;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false, unique = true)
    private String email;

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public void change(UserUpdateDTO userUpdateDTO) {
        this.email = userUpdateDTO.getEmail();
        this.password = Password.passwordEncoding(userUpdateDTO.getPassword());
        this.profileImageUrl = userUpdateDTO.getProfileImageUrl();
        this.phone = userUpdateDTO.getPhone();
        this.nickname = userUpdateDTO.getNickname();
    }

}