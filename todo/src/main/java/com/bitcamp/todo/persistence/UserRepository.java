package com.bitcamp.todo.persistence;

import com.bitcamp.todo.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// <테이블명, primary key의 타입>
public interface UserRepository extends JpaRepository<UserEntity, String> {

    // 아이디로 찾기
    UserEntity findByUsername(String username);

    // 사용자가 존재하는지 검사
    Boolean existsByUsername(String username);

    // 사용자 아이디/패스워드가 같은지 검사
    UserEntity findByUsernameAndPassword(String username, String password);



}
