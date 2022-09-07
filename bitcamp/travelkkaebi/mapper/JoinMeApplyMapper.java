package com.bitcamp.travelkkaebi.mapper;

import com.bitcamp.travelkkaebi.dto.JoinMeApplyResponseDTO;
import com.bitcamp.travelkkaebi.dto.PageAndUserIdDTO;
import com.bitcamp.travelkkaebi.dto.PrimaryIdAndUserIdDTO;
import com.bitcamp.travelkkaebi.model.JoinMeApplyDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface JoinMeApplyMapper {
    int getBoardCountByWriterId(int writerId);
    int getBoardCountByUserId(int userId);
    List<JoinMeApplyResponseDTO> selectAllByWriterId(PageAndUserIdDTO pageAndUserIdDTO);
    List<JoinMeApplyResponseDTO> selectAllByUserId(PageAndUserIdDTO pageAndUserIdDTO);
    Optional<JoinMeApplyResponseDTO> selectOne(int joinMeApplyId);
    Optional<Integer> checkValidUserAndGetJoinMeId(PrimaryIdAndUserIdDTO primaryIdAndUserIdDTO);
    int insert(JoinMeApplyDTO joinMeApplyDTO);
    int updateSelectedTrue(int joinMeApplyId);
}