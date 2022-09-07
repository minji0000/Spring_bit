package com.bitcamp.travelkkaebi.service;

import com.bitcamp.travelkkaebi.mapper.TravelMapMapper;
import com.bitcamp.travelkkaebi.model.TravelMapDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TravelMapService {
    private final TravelMapMapper travelMapMapper;

    public List<TravelMapDTO> selectAll(TravelMapDTO travelMapDTO) throws Exception {
        return travelMapMapper.selectAll(travelMapDTO);
    }

    public boolean insert(TravelMapDTO travelMapDTO, int userId) throws Exception {
        travelMapDTO.setUserId(userId);

        return travelMapMapper.insert(travelMapDTO) == 1 ? true : false;
    }

    @Transactional
    public boolean update(TravelMapDTO travelMapDTO, int userId) throws Exception {
        travelMapDTO.setUserId(userId);

        return travelMapMapper.update(travelMapDTO) == 1 ? true : false;
    }

    @Transactional
    public boolean delete(int travelMapId, int userId) throws Exception {
        TravelMapDTO travelMapDTO = TravelMapDTO.builder().travelMapId(travelMapId).userId(userId).build();

        return travelMapMapper.delete(travelMapDTO)==1 ? true : false;
    }
}
