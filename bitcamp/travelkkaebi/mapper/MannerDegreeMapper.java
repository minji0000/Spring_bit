package com.bitcamp.travelkkaebi.mapper;

import com.bitcamp.travelkkaebi.model.MannerDegreeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface MannerDegreeMapper {
    Optional<MannerDegreeDTO> selectOne(MannerDegreeDTO mannerDegreeDTO);
}