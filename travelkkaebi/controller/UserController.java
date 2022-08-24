package com.bitcamp.travelkkaebi.controller;

import com.bitcamp.travelkkaebi.dto.DeleteUserDTO;
import com.bitcamp.travelkkaebi.dto.LogInDTO;
import com.bitcamp.travelkkaebi.dto.UserDTO;
import com.bitcamp.travelkkaebi.dto.UserUpdateDTO;
import com.bitcamp.travelkkaebi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 회원가입 Create
     */
    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody @Valid UserDTO userDTO) {
        userService.register(userDTO);
        return ResponseEntity.ok().build();
    }

    /**
     * 회원아이디 확인 중복버튼
     */
    @GetMapping("/duplicate")
    public ResponseEntity<Boolean> checkUsername(@RequestParam String username) {
        return ResponseEntity.ok().body(userService.usernameCheck(username));
    }

    /**
     * 로그인
     */
    @PostMapping("/signin")
    public ResponseEntity<LogInDTO> auth(@RequestBody LogInDTO logInDTO) {
        return ResponseEntity.ok().body(userService.auth(logInDTO.getUsername(), logInDTO.getPassword()));
    }

    /**
     * 회원정보수정 Update
     */
    @PutMapping("/update")
    public ResponseEntity<Void> userUpdate(@RequestBody UserUpdateDTO userUpdateDTO) {
        userService.update(userUpdateDTO);
        return ResponseEntity.ok().build();
    }

    /**
     * 회원 delete
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Void> userDelete(@RequestBody DeleteUserDTO deleteUserDTO) {
        userService.delete(deleteUserDTO);
        return ResponseEntity.ok().build();
    }
}