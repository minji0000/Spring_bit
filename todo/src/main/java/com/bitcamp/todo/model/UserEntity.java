package com.bitcamp.todo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
// 유니크 키 설정하는 코드
@Table(name = "todouser", uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
public class UserEntity {

    @Id
    @GeneratedValue(generator = "uuid2") // ID 자동생성
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @Column(nullable = false) // notnull이랑 같은 뜻
    private String username; // 아이디로 사용할 유저 네임: 이메일, 문자열

    private String password;
    private String role;   // 권한
    private String authProvider; // 구글, 카카오, 네이버


}
