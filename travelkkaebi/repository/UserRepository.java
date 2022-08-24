package com.bitcamp.travelkkaebi.repository;

import com.bitcamp.travelkkaebi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Boolean existsByUsername(String username);

    Optional<UserEntity> findByUsername(String username);

    // 제가 추가했는데

}
