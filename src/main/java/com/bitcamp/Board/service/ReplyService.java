package com.bitcamp.Board.service;

import com.bitcamp.Board.model.ReplyDTO;
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

    public void insert(ReplyDTO r) {
        sqlSession.insert(NAMESPACE + ".insert", r);
    }

    public List<ReplyDTO> selectAll(int boardId) {
        return sqlSession.selectList(NAMESPACE + ".selectAll", boardId);
    }
}
