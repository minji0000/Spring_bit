package com.kari.kariblog.service;import com.kari.kariblog.dto.UserDTO;import com.kari.kariblog.entity.UserEntity;import com.kari.kariblog.mapper.UserMapper;import lombok.RequiredArgsConstructor;import org.springframework.stereotype.Service;@Service@RequiredArgsConstructorpublic class UserService {    private final UserMapper userMapper;    /**     * 회원가입     */    public int register(UserEntity userEntity) throws Exception {        return userMapper.save(userEntity);    }}