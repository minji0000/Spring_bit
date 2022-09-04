package com.bitcamp.travelkkaebi.service;

import com.bitcamp.travelkkaebi.dto.JoinMeListDTO;
import com.bitcamp.travelkkaebi.dto.JoinMeOneDTO;
import com.bitcamp.travelkkaebi.dto.PageAndWordDTO;
import com.bitcamp.travelkkaebi.mapper.JoinMeMapper;
import com.bitcamp.travelkkaebi.model.JoinMeDTO;
import com.bitcamp.travelkkaebi.model.LikeOrDislikeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class JoinMeService {
    private final int PAGE_SIZE = 20;
    private final LikeOrDislikeService likeOrDislikeService;
    private final JoinMeMapper joinMeMapper;

    //전체보기 기준 페이지갯수 리턴
    public int getPageCount() throws Exception {
        return calculatePageCount(joinMeMapper.getPageCount());
    }

    //키워드보기 기준 페이지갯수 리턴
    public int getPageCountByKeyword(String keyword) throws Exception {
        return calculatePageCount(joinMeMapper.getPageCountByKeyword(keyword));
    }

    public List<JoinMeListDTO> selectAllByPage(int pageNo) throws Exception {
        List<JoinMeListDTO> joinMeListDTOList = setLikeCount(
                checkClosed(
                        joinMeMapper.selectAllByPage(
                                setPageAndWord(pageNo, null))));

        return joinMeListDTOList;
    }

    public List<JoinMeListDTO> selectAllByPageAndKeyword(int pageNo, String keyword) throws Exception {
        List<JoinMeListDTO> joinMeListDTOList = setLikeCount(
                checkClosed(
                        joinMeMapper.selectAllByPageAndKeyword(
                                setPageAndWord(pageNo, keyword))));

        return joinMeListDTOList;
    }

    public List<JoinMeListDTO> selectAllByPageAndTitle(int pageNo, String searchWord) throws Exception {
        List<JoinMeListDTO> joinMeListDTOList = setLikeCount(
                checkClosed(
                        joinMeMapper.selectAllByPageAndTitle(
                                setPageAndWord(pageNo, searchWord))));
        return joinMeListDTOList;
    }

    public List<JoinMeListDTO> selectAllByPageAndNickname(int pageNo, String searchWord) throws Exception {
        List<JoinMeListDTO> joinMeListDTOList = setLikeCount(
                checkClosed(
                        joinMeMapper.selectAllByPageAndNickname(
                                setPageAndWord(pageNo, searchWord))));
        return joinMeListDTOList;
    }

    //게시물 상세보기하면서 조회수+1
    public JoinMeOneDTO selectOne(int joinMeId) throws Exception {
        if (joinMeMapper.updateView(joinMeId) != 0) { //조회수+1 성공하면
            return joinMeMapper.selectOne(joinMeId)
                    .orElseThrow(() -> new NullPointerException("선택한 게시물이 존재하지 않음"));
        } else { //게시물이 존재하지 않으면
            throw new RuntimeException("게시물 조회수 갱신 실패");
        }
    }

    public JoinMeOneDTO insert(JoinMeDTO joinMeDTO, int userId) throws Exception {
        joinMeDTO.setUserId(userId);

        if (joinMeMapper.insert(joinMeDTO) != 0) { //insert가 성공했으면
            //useGenerateKeys에 의해 생성된 Id값으로 selectOne해서 리턴
            return joinMeMapper.selectOne(joinMeDTO.getJoinMeId())
                    .orElseThrow(() -> new NullPointerException("입력한 게시물이 존재하지 않음"));
        } else { //삽입 실패했으면
            throw new RuntimeException("게시물 삽입 실패");
        }
    }

    @Transactional
    public JoinMeOneDTO update(JoinMeDTO joinMeDTO, int userId) throws Exception {
        //CSRF방어
        joinMeDTO.setUserId(userId);
        if (joinMeMapper.update(joinMeDTO) != 0) {
            //useGenerateKeys에 의해 생성된 Id값으로 selectOne해서 리턴
            return selectOne(joinMeDTO.getJoinMeId());
        } else { //삽입 실패했으면
            throw new RuntimeException("게시물 업데이트 실패");
        }
    }

    @Transactional
    public boolean delete(JoinMeDTO joinMeDTO, int userId) throws Exception {
        //CSRF방어
        joinMeDTO.setUserId(userId);

        return (joinMeMapper.delete(joinMeDTO) != 0 ? true : false);
    }

    //페이지번호와 키워드를 객체에 세팅해주는 메소드
    public PageAndWordDTO setPageAndWord(int pageNo, String word) {
        return PageAndWordDTO.builder()
                .startNum((pageNo - 1) * PAGE_SIZE)
                .pageSize(PAGE_SIZE)
                .word(word)
                .build();
    }

    //여행 끝나는날을 기준으로 글을 마감처리하는 메소드
    @Transactional
    public List<JoinMeListDTO> checkClosed(List<JoinMeListDTO> joinMeList) throws Exception {
        for (int i = 0; i < joinMeList.size(); i++) {
            JoinMeListDTO joinMeListDTO = joinMeList.get(i);
            //여행 끝나는날이 현재시간기준 과거면
            if (joinMeListDTO.getEndDate().getTime() < System.currentTimeMillis()) {
                //글을 마감처리하고
                joinMeListDTO.setClosed(true);
                //마감처리된 글을 update
                if (joinMeMapper.updateClosed(joinMeListDTO) == 0) {
                    throw new RuntimeException("마감처리 업데이트 실패");
                }
            }
        }
        return joinMeList;
    }

    //응답객체에 좋아요 갯수 삽입해주는 메소드
    public List<JoinMeListDTO> setLikeCount(List<JoinMeListDTO> joinMeList) throws Exception {
        for (int i = 0; i < joinMeList.size(); i++) {
            JoinMeListDTO joinMeListDTO = joinMeList.get(i);
            LikeOrDislikeDTO likeOrDislikeDTO = LikeOrDislikeDTO.builder()
                    .categoryId(joinMeListDTO.getCategoryId())
                    .boardId(joinMeListDTO.getJoinMeId())
                    .build();
            joinMeListDTO.setLikeCount(likeOrDislikeService.getLikeCount(likeOrDislikeDTO));
        }
        return joinMeList;
    }

    //페이지수 계산해주는 메소드
    private int calculatePageCount(int boardCount) {
        if (boardCount % PAGE_SIZE != 0) {
            return boardCount / PAGE_SIZE + 1;
        } else {
            return boardCount / PAGE_SIZE;
        }
    }
}