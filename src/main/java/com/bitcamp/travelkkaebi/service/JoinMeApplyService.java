package com.bitcamp.travelkkaebi.service;

import com.bitcamp.travelkkaebi.dto.JoinMeApplyResponseDTO;
import com.bitcamp.travelkkaebi.dto.JoinMeOneDTO;
import com.bitcamp.travelkkaebi.dto.PageAndUserIdDTO;
import com.bitcamp.travelkkaebi.dto.PageAndWordDTO;
import com.bitcamp.travelkkaebi.mapper.JoinMeApplyMapper;
import com.bitcamp.travelkkaebi.mapper.JoinMeMapper;
import com.bitcamp.travelkkaebi.model.JoinMeApplyDTO;
import com.bitcamp.travelkkaebi.model.JoinMeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JoinMeApplyService {
    private final int PAGE_SIZE = 20;
    private final JoinMeApplyMapper joinMeApplyMapper;
    private final JoinMeMapper joinMeMapper;

    public boolean insert(JoinMeApplyDTO joinMeApplyDTO, int userId) throws Exception {
        joinMeApplyDTO.setUserId(userId);
        //해당 신청서의 게시물 가져오기
        JoinMeOneDTO joinMeOneDTO = joinMeMapper.selectOne(joinMeApplyDTO.getJoinMeId())
                .orElseThrow(() -> new NullPointerException("게시물이 존재하지 않음"));
        //게시물의 신청가능 인원이 가득 찼는지 검사
        if (joinMeOneDTO.getCapacity() > joinMeOneDTO.getCurrentMemberCount()) {
            return joinMeApplyMapper.insert(joinMeApplyDTO) == 1 ? true : false;
        } else {
            throw new RuntimeException("신청가능인원이 가득참");
        }
    }

    public List<JoinMeApplyResponseDTO> selectAllByUserId(int pageNo, int userId) throws Exception {
        PageAndUserIdDTO pageAndUserIdDTO = PageAndUserIdDTO.builder()
                .startNum((pageNo - 1) * PAGE_SIZE)
                .pageSize(PAGE_SIZE)
                .user_id(userId)
                .build();
        return joinMeApplyMapper.selectAllByUserId(pageAndUserIdDTO);
    }
}