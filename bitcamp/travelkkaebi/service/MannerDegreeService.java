package com.bitcamp.travelkkaebi.service;

import com.bitcamp.travelkkaebi.mapper.MannerDegreeMapper;
import com.bitcamp.travelkkaebi.model.MannerDegreeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MannerDegreeService {
    private final MannerDegreeMapper mannerDegreeMapper;

    public MannerDegreeDTO selectOne(int toUserId, int fromUserId) {
        return mannerDegreeMapper.selectOne(
                MannerDegreeDTO.builder()
                .toUserId(toUserId)
                .fromUserId(fromUserId)
                .build()).orElse(insert(toUserId, fromUserId));
    }

    public MannerDegreeDTO insert(int toUserId, int fromUserId){
        return null;
    }
}