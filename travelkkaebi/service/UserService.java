package com.bitcamp.travelkkaebi.service;

import com.bitcamp.travelkkaebi.entity.UserEntity;
import com.bitcamp.travelkkaebi.repository.UserRepository;
import com.bitcamp.travelkkaebi.security.TokenProvider;
import com.bitcamp.travelkkaebi.dto.DeleteUserDTO;
import com.bitcamp.travelkkaebi.dto.LogInDTO;
import com.bitcamp.travelkkaebi.dto.UserDTO;
import com.bitcamp.travelkkaebi.dto.UserUpdateDTO;
import com.bitcamp.travelkkaebi.encode.Password;
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
    public void register(UserDTO userDTO) {
        //username 중복체크
        validate(userDTO.getUsername());

        //dto -> entity 변환후 save
        userRepository.save(UserDTO.toUserEntity(userDTO));
    }

    private void validate(String username) {
        if (usernameCheck(username))
            throw new RuntimeException("already exist username...");
    }

    /**
     * username 중복체크 logic
     */
    public boolean usernameCheck(String username) {
        return userRepository.existsByUsername(username);
    }

    /**
     * 로그인 logic
     */
    public LogInDTO auth(String username, String password) {
        UserEntity findUser = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("does not exist"));
        if (Password.passwordMatch(password, findUser.getPassword())) {
            // create token
            String token = tokenProvider.create(findUser);
            //발급된 토큰과 함께 리턴
            return LogInDTO.builder()
                    .username(findUser.getUsername())
                    .id(findUser.getId())
                    .token(token)
                    .build();
        }
        return null;
    }

    /**
     * 회원정보 update logic
     */
    @Transactional
    public void update(UserUpdateDTO userUpdateDTO) {
        UserEntity findUser = userRepository.findById(userUpdateDTO.getUserid()).orElseThrow(() -> new RuntimeException("update exception"));
        findUser.change(userUpdateDTO);
    }

    /**
     *  회원정보 delete logic
     */
    @Transactional
    public void delete(DeleteUserDTO deleteUserDTO) {
        UserEntity deleteUser = userRepository.findById(deleteUserDTO.getUserid()).orElseThrow(() -> new RuntimeException("delete exception"));
        if (Password.passwordMatch(deleteUserDTO.getPassword(), deleteUser.getPassword())) {
            userRepository.delete(deleteUser);
        }
    }
    
    
}
