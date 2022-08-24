package com.bitcamp.travelkkaebi.repository;

import com.bitcamp.travelkkaebi.model.ReviewDTO;
import com.bitcamp.travelkkaebi.model.ReviewReplyDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewReplyRepository {

    private final String NAMESPACE = "src.main.resources.mapper.ReviewReplyMapper";
    private SqlSession sqlSession;


    public int insert(ReviewReplyDTO reply) {
        int success = sqlSession.insert(NAMESPACE + ".insert", reply);
        int writtenReplyId = reply.getReviewReplyId();

        return success * writtenReplyId;
    }

    public int update(ReviewReplyDTO reply) {
        int success = sqlSession.update(NAMESPACE + ".update", reply);
        int updatedId = reply.getReviewReplyId();

        // 성공하면 생성된 id 값 리턴, 실패하면 0 리턴
        return success * updatedId;
    }

    public int delete(int replyId) {
        // 성공하면 1, 실패하면 0 리턴
        return sqlSession.delete(NAMESPACE+".delete", replyId);
    }

    public List<ReviewReplyDTO> selectOne(int reviewId) {
        List<ReviewReplyDTO> list = sqlSession.selectOne(NAMESPACE+ ".selectByReviewId", reviewId);
        return list;
    }


}
