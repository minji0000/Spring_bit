package com.bitcamp.todo.service;

import com.bitcamp.todo.model.UserEntity;
import com.bitcamp.todo.persistence.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 아이디(이메일) 유효성 및 중복검사
    public UserEntity create(final UserEntity userEntity) {
        // 1. userEntity 유효성 체크
        if(userEntity == null || userEntity.getUsername() == null) {
            // 예외 던지기
            throw new RuntimeException("Invalid arguments");
        }
        final String username = userEntity.getUsername();

        // 2. 중복검사
        if(userRepository.existsByUsername(username)) {
            // 있으면 경고 띄어주기
            log.warn("Username already exists {}", username);
            // 또 예외 던지기
            throw new RuntimeException("username already exists");
        }

        return userRepository.save(userEntity);
    }


    // 로그인 아이디 & 비밀번호 일치 확인

    public UserEntity getByCredentials(final String username, final String password,
                                   final PasswordEncoder encoder)
    {
        // DB에 있는 사용자 계정 정보
        final UserEntity originalUser = userRepository.findByUsername(username);

        // matches 메서드를 이용해 패스워드가 같은지 확인
        if(originalUser != null && encoder.matches(password, originalUser.getPassword())) {
            return originalUser;
        }

        return null;
    }
















}
