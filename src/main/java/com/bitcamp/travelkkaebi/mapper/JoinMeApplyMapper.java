package com.bitcamp.travelkkaebi.mapper;

import com.bitcamp.travelkkaebi.dto.JoinMeApplyResponseDTO;
import com.bitcamp.travelkkaebi.dto.PageAndUserIdDTO;
import com.bitcamp.travelkkaebi.model.JoinMeApplyDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JoinMeApplyMapper {
    List<JoinMeApplyResponseDTO> selectAllByWriterId(PageAndUserIdDTO pageAndUserIdDTO);
    List<JoinMeApplyResponseDTO> selectAllByUserId(PageAndUserIdDTO pageAndUserIdDTO);
    int insert(JoinMeApplyDTO joinMeApplyDTO);
}