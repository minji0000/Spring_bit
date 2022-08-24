package com.bitcamp.travelkkaebi.repository;

import com.bitcamp.travelkkaebi.model.LikeOrDislikeDTO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class LikeOrDislikeRepository {
    private final String NAMESPACE = "mapper.LikeOrDislikeMapper";
    SqlSession sqlSession;

    public LikeOrDislikeDTO selectOne(LikeOrDislikeDTO l){
        return sqlSession.selectOne(NAMESPACE+".selectOne", l);
    }

    public int insert(LikeOrDislikeDTO l){
        int success = sqlSession.insert(NAMESPACE+".insert", l);
        int insertedId = l.getLikeOrDislikeId();
        //성공하면 생성된 id값 리턴, 실패하면 0 리턴
        return success*insertedId;
    }

    public int update(LikeOrDislikeDTO l){
        int success = sqlSession.update(NAMESPACE+"update", l);
        int updatedId = l.getLikeOrDislikeId();
        //성공하면 생성된 id값 리턴, 실패하면 0 리턴
        return success*updatedId;
    }

    public int delete(LikeOrDislikeDTO l){
        //성공하면 1 실패하면 0 리턴
        return sqlSession.delete(NAMESPACE+".delete", l);
    }
}