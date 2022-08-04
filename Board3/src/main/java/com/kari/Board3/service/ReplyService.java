package com.kari.Board3.service;

import com.kari.Board3.model.ReplyDTO;
import com.kari.Board3.model.UserDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
@Service
public class ReplyService {
    private final String NAMESPACE = "mapper.ReplyMapper";

    @Autowired
    SqlSession sqlSession;

    // 파라미터로 들어온 reply r 객체를
    // 데이터베이스에 등록하는 코드이다.
    // 즉 댓글을 등록하는 코드
    public void insert(ReplyDTO r) {
        sqlSession.insert(NAMESPACE + ".insert", r);
    }

    // 파라미터로 들어온 int 타입의 boardId를
    // replyMapper의 selectAll()로 리턴해
    // 게시물을 보여주는 코드이다.
    public List<ReplyDTO> selectAll(int boardId) {
        // boardId가 파라미터로 들어오는 이유:
        // 게시물에 달린 해당 댓글을 보여주어야 하기 때문
        return sqlSession.selectList(NAMESPACE + ".selectAll", boardId);
    }
}