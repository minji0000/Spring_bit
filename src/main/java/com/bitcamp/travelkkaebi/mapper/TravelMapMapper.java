package com.bitcamp.travelkkaebi.mapper;

import com.bitcamp.travelkkaebi.model.TravelMapDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TravelMapMapper {
    List<TravelMapDTO> selectAll(TravelMapDTO travelMapDTO);
    TravelMapDTO selectOne(int travelMapId);
    int insert(TravelMapDTO travelMapDTO);
    int update(TravelMapDTO travelMapDTO);
    int delete(TravelMapDTO travelMapDTO);
}