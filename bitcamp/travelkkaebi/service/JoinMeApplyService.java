package com.bitcamp.travelkkaebi.service;

import com.bitcamp.travelkkaebi.dto.*;
import com.bitcamp.travelkkaebi.mapper.JoinMeApplyMapper;
import com.bitcamp.travelkkaebi.mapper.JoinMeMapper;
import com.bitcamp.travelkkaebi.model.JoinMeApplyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JoinMeApplyService {
    private final int PAGE_SIZE = 10;
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

    //로그인한 유저가 신청한 신청서들
    public ListResponseDTO selectAllByUserId(int pageNo, int userId) throws Exception {
        List<JoinMeApplyResponseDTO> joinMeApplyResponseDTOList
                = joinMeApplyMapper.selectAllByUserId(setPageAndUserId(pageNo, userId));

        return setListResponseDTO(joinMeApplyMapper.getBoardCountByUserId(userId), joinMeApplyResponseDTOList);
    }

    //로그인한 유저의 같이가요 글에 신청한 신청서들
    public ListResponseDTO selectAllByWriterId(int pageNo, int userId) throws Exception {
        List<JoinMeApplyResponseDTO> joinMeApplyResponseDTOList
                = joinMeApplyMapper.selectAllByWriterId(setPageAndUserId(pageNo, userId));
        return setListResponseDTO(joinMeApplyMapper.getBoardCountByWriterId(userId), joinMeApplyResponseDTOList);
    }

    public JoinMeApplyResponseDTO selectOne(int joinMeApplyId) throws Exception {
        return joinMeApplyMapper.selectOne(joinMeApplyId)
                .orElseThrow(() -> new NullPointerException("해당 신청서가 존재하지 않음"));
    }

    //신청서 채택하는 메소드
    @Transactional
    public boolean setSelectedTrue(int joinMeApplyId, int userId) throws Exception {
        int isMemberUpdated=0;
        //로그인한유저의 게시물인지 판별하고 joinMeId 리턴
        int joinMeId = joinMeApplyMapper.checkValidUserAndGetJoinMeId(setJoinMeIdAndUserIdDTO(userId, joinMeApplyId))
                .orElseThrow(() -> new NullPointerException("신청서의 대상이 로그인한 유저가 아니거나 게시물이 존재하지 않음"));
        //해당 신청서의 게시물 가져오기
        JoinMeOneDTO joinMeOneDTO = joinMeMapper.selectOne(joinMeId)
                .orElseThrow(() -> new NullPointerException("게시물이 존재하지 않음"));
        //해당 게시물의 신청가능인원이 남아있는지 확인
        if (joinMeOneDTO.getCapacity() > joinMeOneDTO.getCurrentMemberCount()) {
            //신청인원변화(countChange)을 +1해서 update하고 성공하면 1저장
            isMemberUpdated = joinMeMapper.updateMemberCount(setMemberCount(joinMeOneDTO, 1));
        } else {
            throw new RuntimeException("신청가능인원이 가득참");
        }
        //countChange 업데이트 성공 & 신청서 selected 를 True로 업데이트성공하면 True 리턴
        return (joinMeApplyMapper.updateSelectedTrue(joinMeApplyId) * isMemberUpdated == 1);
    }

    //페이지와 userId를 객체에 세팅해주는 메소드
    public PageAndUserIdDTO setPageAndUserId(int pageNo, int userId) {
        return PageAndUserIdDTO.builder()
                .startNum((pageNo - 1) * PAGE_SIZE)
                .pageSize(PAGE_SIZE)
                .userId(userId)
                .build();
    }

    //joinMeApplyId와 userId를 객체에 세팅해주는 매소드
    private PrimaryIdAndUserIdDTO setJoinMeIdAndUserIdDTO(int userId, int joinMeApplyId){
        return PrimaryIdAndUserIdDTO.builder()
                .primaryId(joinMeApplyId)
                .userId(userId)
                .build();
    }

    //countChange(+1, -1 등등)만큼 현재멤버 수 변경해서 객체에 set
    public UpdateMemberCountDTO setMemberCount(JoinMeOneDTO joinMeOneDTO, int countChange) {
        return UpdateMemberCountDTO.builder()
                .userId(joinMeOneDTO.getUserId())
                .joinMeId(joinMeOneDTO.getJoinMeId())
                .currentMemberCount(joinMeOneDTO.getCurrentMemberCount() + countChange)
                .build();
    }

    //응답객체에 게시물리스트와 총페이지수 세팅해주는 메소드
    public ListResponseDTO setListResponseDTO(int totalPageCount, List<JoinMeApplyResponseDTO> joinMeApplyResponseDTOList) {
        return ListResponseDTO.builder()
                .totalBoardCount(totalPageCount)
                .list(joinMeApplyResponseDTOList)
                .build();
    }

//    //페이지수 계산해주는 메소드
//    private int calculatePageCount(int boardCount) {
//        if (boardCount % PAGE_SIZE != 0) {
//            return boardCount / PAGE_SIZE + 1;
//        } else {
//            return boardCount / PAGE_SIZE;
//        }
//    }
}