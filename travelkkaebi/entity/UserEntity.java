package com.bitcamp.travelkkaebi.entity;

import com.bitcamp.travelkkaebi.dto.UserUpdateDTO;
import com.bitcamp.travelkkaebi.encode.Password;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class UserEntity extends BaseEntity {
    static final int DEFAULT_MANNER_DEGREE = 37;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole role;

    @Enumerated(EnumType.STRING)
    @Column(name = "sex", nullable = false)
    private Gender gender;

    @Column(name = "profile_image_url", nullable = false)
    private String profileImageUrl;

    @Column(name = "blocked_until")
    private LocalDateTime blockedUntil;

    @Column(name = "manner_degree", nullable = false)
    private int mannerDegree = DEFAULT_MANNER_DEGREE;

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

    @Column(nullable = false)
    private String region;

    public void change(UserUpdateDTO userUpdateDTO) {
        this.email = userUpdateDTO.getEmail();
        this.password = Password.passwordEncoding(userUpdateDTO.getPassword());
        this.profileImageUrl = userUpdateDTO.getProfileImageUrl();
        this.region = userUpdateDTO.getRegion();
        this.phone = userUpdateDTO.getPhone();
        this.nickname = userUpdateDTO.getNickname();
    }
}