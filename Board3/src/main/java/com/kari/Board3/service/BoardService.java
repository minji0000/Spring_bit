package com.kari.Board3.service;

import com.kari.Board3.model.BoardDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Repository
@Service
public class BoardService {
    private final String NAMESPACE = "mapper.BoardMapper";
    @Autowired
    SqlSession sqlSession;
    private final int PAGE_SIZE = 10;

    // 파라미터로 들어온 board b 객체를
    // 데이터베이스에 등록하는 코드이다.
    // 즉 게시물을 등록하는 코드
    public void insert(BoardDTO b) {
        sqlSession.insert(NAMESPACE + ".insert", b);
    }

    // 파라미터로 들어온 int 타입의 pageNo 객체로
    // 게시물을 보여주는 코드이다.
    public List<BoardDTO> selectAll(int pageNo) {
        // HashMap을 선언해
        // key가 String, value를 Integer 타입으로 저장한다.
        HashMap<String, Integer> map = new HashMap<>();

        // 데이터 베이스의 index는 0부터 시작하므로 -1을 해준다.
        // 그 페이지를 PAGE_SIZE(= 몇 개씩 보여줄지) 만큼 가져온다.
        int startNum = (pageNo - 1) * PAGE_SIZE ;

        // map 에 startNum이라는 이름으로 startNum 객체를 넣어주는 코드이다.
        map.put("startNum", startNum);
        // map 에 PAGE_SIZE 라는 이름으로 PAGE_SIZE 객체를 넣어주는 코드이다.
        // 이는 변함없는 상수이므로 대문자로 표기한다.
        map.put("PAGE_SIZE", PAGE_SIZE);

        // map에 담은 객체를
        // boardMapper의 selectAll 메소드로 리턴한다.
        return sqlSession.selectList(NAMESPACE + ".selectAll", map);
    }

    // 게시물 상세보기를 위한 메소드
    // 파라미터로 들어온 int 타입의 아이디는 boardId이다.
    public BoardDTO selectOne(int id) {
        // 파라미터로 들어온 id를
        //boardMapper의 selectOne 메소드로 리턴한다.
        return sqlSession.selectOne(NAMESPACE + ".selectOne", id);
    }


    public int selectLastPage() {
        // 게시물의 갯수가 몇 개인지를 boardMapper.selectOne 메소드를 실행시켜
        // total에 담아준다.
        int total = sqlSession.selectOne(NAMESPACE + ".count");

        // 페이지의 갯수가 pagesize로 나눴을 때 0으로 떨어지면
        // 그대로 리턴하고
        if(total % PAGE_SIZE == 0) {
            return total / PAGE_SIZE;

            // 0으로 떨어지지 않는다면 그 값에서 +1을 하여 리턴한다.
            // +1을 하는 이유는 페이지 사이즈 만큼 게시글을 보여줄건데
            // 73개의 게시물이 있으면 나머지 3개는 보여지지 않기 때문에
            // 70/10으로 나눠 7페이지가 나오면 3개도 보여주기 위해 +1로
            // 페이지를 8로 나눠주기 위함이다.
        } else {
            return (total/ PAGE_SIZE) + 1;
        }
    }

    // 게시물을 수정하기 위한 코드
    // 수정사항이 담기기 때문에 파라미터의 자료형은 BoardDTO 타입이다.
    public void update(BoardDTO b) {
        sqlSession.update(NAMESPACE + ".update", b);
    }

    // 게시물을 삭제하기 위한 코드
    // boardId를 통해 삭제하기 때문에 id이다.
    public void delete(int id) {
        sqlSession.delete(NAMESPACE + ".delete", id);
    }

    // 게시물을 여러 개 추가하기 위한 메소드
    public void init(BoardDTO b) {
        sqlSession.insert(NAMESPACE + ".init", b);
    }
}
