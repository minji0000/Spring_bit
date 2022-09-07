package com.bitcamp.travelkkaebi.repository;

import com.bitcamp.travelkkaebi.entity.PickMeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PickMeRepository extends JpaRepository<PickMeEntity, Integer> {
    Page<PickMeEntity> findByOrderByIdDesc(Pageable pageable);
}
