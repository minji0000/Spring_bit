package com.bitcamp.travelkkaebi.service;

import com.bitcamp.travelkkaebi.repository.LikeOrDislikeRepository;
import com.bitcamp.travelkkaebi.model.LikeOrDislikeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class LikeOrDislikeService {
    private final LikeOrDislikeRepository likeOrDislikeRepository;

    //게시물 상세보기를 했을 때 좋아요, 싫어요의 체크상태 리턴해주는 메소드
    public HashMap<String, Boolean> isLikeOrDislikeChecked(LikeOrDislikeDTO l) {
        try{
            //해당 게시물의 좋아요, 싫어요 상태를 가져오기
            HashMap<String, Boolean> likeMap = new HashMap<>();

            l = likeOrDislikeRepository.selectOne(l);
            likeMap.put("liked", l.isLiked());
            likeMap.put("disliked", l.isDisliked());

            return likeMap;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    public int createLikeOrDislike(LikeOrDislikeDTO l){
        try{
            int likeOrDislikeId = likeOrDislikeRepository.insert(l);
            //성공했을 경우 likeOrDislikeId리턴, 실패했을 경우 0 리턴
            return likeOrDislikeId;
        } catch(Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //게시물의 좋아요를 클릭했을 때 실행 할 메소드
    public int clickLike(LikeOrDislikeDTO l) {
        int likeOrDislikeId;

        try{
            if(l.isLiked()==true&&l.isDisliked()==false){ //좋아요가 클릭되어 있었을 경우
                l.setLiked(false);
            } else if(l.isLiked()==false&&l.isDisliked()==false){ //아무것도 클릭되어있지 않았을 경우
                l.setLiked(true);
            } else if(l.isLiked()==false&&l.isDisliked()==true){ //싫어요가 클릭되어 있었을 경우
                l.setLiked(true);
                l.setDisliked(false);
            }
            likeOrDislikeId = likeOrDislikeRepository.update(l);

        } catch (Exception e){
            e.printStackTrace();
            return 0;
        }

        //성공했을 경우 likeOrDislikeId리턴, 실패했을 경우 0 리턴
        return likeOrDislikeId;
    }
    //게시물의 싫어요를 클릭했을 때 실행 할 메소드
    public int clickDislike(LikeOrDislikeDTO l){
        if(l.isLiked()==true&&l.isDisliked()==false){ //좋아요가 클릭되어 있었을 경우
            l.setLiked(false);
            l.setDisliked(true);
        } else if(l.isLiked()==false&&l.isDisliked()==false){ //아무것도 클릭되어있지 않았을 경우
            l.setDisliked(true);
        } else if(l.isLiked()==false&&l.isDisliked()==true){ //싫어요가 클릭되어 있었을 경우
            l.setDisliked(false);
        }

        int likeOrDislikeId = likeOrDislikeRepository.update(l);
        //성공했을 경우 likeOrDislikeId리턴, 실패했을 경우 0 리턴
        return likeOrDislikeId;
    }
}