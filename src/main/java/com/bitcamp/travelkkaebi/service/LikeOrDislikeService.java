package com.bitcamp.travelkkaebi.service;

import com.bitcamp.travelkkaebi.dto.LikeOrDislikeResponseDTO;
import com.bitcamp.travelkkaebi.mapper.LikeOrDislikeMapper;
import com.bitcamp.travelkkaebi.model.LikeOrDislikeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class LikeOrDislikeService {
    private final LikeOrDislikeMapper likeOrDislikeMapper;

    //게시물 상세보기를 했을 때 좋아요, 싫어요의 체크상태 리턴해주는 메소드
    public LikeOrDislikeResponseDTO selectOne(LikeOrDislikeDTO likeOrDislikeDTO, int userId) throws Exception {
        likeOrDislikeDTO.setUserId(userId);
        //로그인한 유저의 해당 게시물에 대한 좋-싫 테이블이 존재하는지 확인후 없으면 생성(게시물을 본적 있는지)
        LikeOrDislikeResponseDTO likeOrDislikeResponseDTO = likeOrDislikeMapper.selectOneByDTO(likeOrDislikeDTO)
                .orElse(likeOrDislikeMapper.selectOneById(insert(likeOrDislikeDTO)).get());

        return setCounts(likeOrDislikeResponseDTO);
    }

    //좋아요-싫어요 테이블 생성후 id리턴
    private int insert(LikeOrDislikeDTO likeOrDislikeDTO) throws Exception {
        //삽입
        likeOrDislikeMapper.insert(likeOrDislikeDTO);
        //useGeneratedKeys에 의해 생성된 id리턴
        return likeOrDislikeDTO.getLikeOrDislikeId();
    }

    //게시물의 좋아요를 클릭했을 때 실행 할 메소드
    @Transactional
    public LikeOrDislikeResponseDTO clickLike(int likeOrDislikeId, int userId) throws Exception {
        //로그인한 유저의 식별자를 삽입(중간에 가로채서 접근할 수 있으므로 userId갱신)
        LikeOrDislikeDTO likeOrDislikeDTO = likeOrDislikeMapper.selectOneById(likeOrDislikeId)
                .orElseThrow(() -> new NullPointerException("해당 likeOrDislikeId의 레코드가 없음"));

        if (likeOrDislikeDTO.isLiked() == true && likeOrDislikeDTO.isDisliked() == false) { //좋아요가 클릭되어 있었을 경우
            likeOrDislikeDTO.setLiked(false);
        } else if (likeOrDislikeDTO.isLiked() == false && likeOrDislikeDTO.isDisliked() == false) { //아무것도 클릭되어있지 않았을 경우
            likeOrDislikeDTO.setLiked(true);
        } else if (likeOrDislikeDTO.isLiked() == false && likeOrDislikeDTO.isDisliked() == true) { //싫어요가 클릭되어 있었을 경우
            likeOrDislikeDTO.setLiked(true);
            likeOrDislikeDTO.setDisliked(false);
        }
        //CSRF방어
        likeOrDislikeDTO.setUserId(userId);
        //상태업데이트하면서 count추가해 리턴
        return updateClickStatus(likeOrDislikeDTO);
    }

    //게시물의 싫어요를 클릭했을 때 실행 할 메소드
    @Transactional
    public LikeOrDislikeResponseDTO clickDislike(int likeOrDislikeId, int userId) throws Exception {
        //로그인한 유저의 식별자를 삽입(중간에 가로채서 접근할 수 있으므로 userId갱신)
        LikeOrDislikeDTO likeOrDislikeDTO = likeOrDislikeMapper.selectOneById(likeOrDislikeId)
                .orElseThrow(() -> new NullPointerException("해당 likeOrDislikeId의 레코드가 없음"));

        if (likeOrDislikeDTO.isLiked() == true && likeOrDislikeDTO.isDisliked() == false) { //좋아요가 클릭되어 있었을 경우
            likeOrDislikeDTO.setLiked(false);
            likeOrDislikeDTO.setDisliked(true);
        } else if (likeOrDislikeDTO.isLiked() == false && likeOrDislikeDTO.isDisliked() == false) { //아무것도 클릭되어있지 않았을 경우
            likeOrDislikeDTO.setDisliked(true);
        } else if (likeOrDislikeDTO.isLiked() == false && likeOrDislikeDTO.isDisliked() == true) { //싫어요가 클릭되어 있었을 경우
            likeOrDislikeDTO.setDisliked(false);
        }
        //CSRF방어
        likeOrDislikeDTO.setUserId(userId);
        //상태업데이트하면서 count추가해 리턴
        return updateClickStatus(likeOrDislikeDTO);
    }

    //게시물의 좋아요 갯수를 리턴해주는 메소드
    public int getLikeCount(LikeOrDislikeDTO likeOrDislikeDTO) throws Exception{
        return likeOrDislikeMapper.getLikeCount(likeOrDislikeDTO);
    }

    //게시물의 싫어요 갯수를 리턴해주는 메소드
    public int getDislikeCount(LikeOrDislikeDTO likeOrDislikeDTO) throws Exception{
        return likeOrDislikeMapper.getDislikeCount(likeOrDislikeDTO);
    }

    @Transactional
    public LikeOrDislikeResponseDTO updateClickStatus(LikeOrDislikeDTO likeOrDislikeDTO) throws Exception {
        //상태 업데이트하고 성공했다면
        if (likeOrDislikeMapper.update(likeOrDislikeDTO) != 0) {
            //update성공했으면 리턴
            LikeOrDislikeResponseDTO likeOrDislikeResponseDTO =
                    likeOrDislikeMapper.selectOneById(likeOrDislikeDTO.getLikeOrDislikeId()).get();

            return setCounts(likeOrDislikeResponseDTO);
        } else {
            throw new RuntimeException("상태 업데이트 실패");
        }
    }

    public LikeOrDislikeResponseDTO setCounts(LikeOrDislikeResponseDTO likeOrDislikeResponseDTO) throws Exception {
        likeOrDislikeResponseDTO.setLikeCount(getLikeCount(likeOrDislikeResponseDTO));
        likeOrDislikeResponseDTO.setDislikeCount(getDislikeCount(likeOrDislikeResponseDTO));

        return likeOrDislikeResponseDTO;
    }
}