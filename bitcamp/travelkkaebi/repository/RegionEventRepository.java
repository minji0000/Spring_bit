package com.bitcamp.travelkkaebi.repository;

import com.bitcamp.travelkkaebi.entity.RegionalEventEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionEventRepository extends JpaRepository<RegionalEventEntity, Integer> {
    Page<RegionalEventEntity> findByOrderByIdDesc(Pageable pageable);
}
