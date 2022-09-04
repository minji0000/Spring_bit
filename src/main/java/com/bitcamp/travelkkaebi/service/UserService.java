package com.bitcamp.travelkkaebi.service;

import com.bitcamp.travelkkaebi.dto.*;
import com.bitcamp.travelkkaebi.encode.Password;
import com.bitcamp.travelkkaebi.entity.UserEntity;
import com.bitcamp.travelkkaebi.repository.UserRepository;
import com.bitcamp.travelkkaebi.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;

    /**
     * 회원가입 logic
     */
    @Transactional
    public void register(UserDTO userDTO, String uploadImageUrl) {
        //username 및 email 중복체크 method
        validate(userDTO.getUsername(), userDTO.getEmail());

        //dto, imageUrl -> entity 변환후 DB save
        UserEntity userEntity = UserDTO.toUserEntity(userDTO);
        userEntity.setProfileImageUrl(uploadImageUrl);

        userRepository.save(userEntity);
    }

    /**
     * username, email, nickname 중복체크 logic 존재 -> true, 존재x -> false
     */
    public boolean usernameCheck(String username) {
        return userRepository.existsByUsername(username);
    }

    private boolean emailCheck(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean nicknameCheck(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    private void validate(String username, String email) {
        if (usernameCheck(username) || emailCheck(email))
            throw new RuntimeException("already exist username...");
    }

    /**
     * 로그인 logic
     */
    public LogInDTO auth(String username, String password) {
        UserEntity findUser = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("does not exist"));
        //password 일치 check
        if (Password.passwordMatch(password, findUser.getPassword())) {
            // create token
            String token = tokenProvider.create(findUser);
            //발급된 토큰및 entity -> dto 변환후 함께 리턴
            return LogInDTO.toDto(findUser, token);
        }
        return null;
    }

    /**
     * 회원정보 update logic
     */
    @Transactional
    public void update(UserUpdateDTO userUpdateDTO, String uploadImageUrl) {
        UserEntity findUser = userRepository.findById(userUpdateDTO.getUserid()).orElseThrow(() -> new RuntimeException("update exception"));
        userUpdateDTO.setProfileImageUrl(uploadImageUrl);
        findUser.change(userUpdateDTO);
    }

    /**
     * 회원정보 delete logic
     */
    @Transactional
    public void delete(DeleteUserDTO deleteUserDTO) {
        UserEntity deleteUser = userRepository.findById(deleteUserDTO.getUserid()).orElseThrow(() -> new RuntimeException("delete exception"));
        if (Password.passwordMatch(deleteUserDTO.getPassword(), deleteUser.getPassword())) {
            userRepository.delete(deleteUser);
        }
    }

}
