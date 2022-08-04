package com.kari.Board3.service;

import com.kari.Board3.model.UserDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
@Repository
@Service
public class UserService {

    private final String NAMESPACE = "mapper.UserMapper";

    @Autowired
    private SqlSession sqlSession;

    // 아이디와 비밀번호가 일치하는지 알아보는 메소드이다.
    // 해당 아이디에 해당 비밀번호가 들어있어야 하기 때문에
    // 아이디와 비밀번호 검사를 할 수 있다.

    public UserDTO auth(UserDTO u) {
        // 파라미터로 들어온 u 객체는 index.jsp에서 받아온다.
        // UserMapper의 auth() 메소드로 u를 리턴한다.
        return sqlSession.selectOne(NAMESPACE+ ".auth", u);
    }

    // 회원가입을 하기 위한 메소드이다.
    // user 폴더의 register.jsp에서 파라미터로 u객체를 받아온다.
    public void register(UserDTO u) {
        // 파라미터로 들어온 u 객체는 index.jsp에서 받아온다.
        // UserMapper의 register() 메소드로 u를 리턴한다.
        sqlSession.insert(NAMESPACE+ ".register", u);

    }

    // 파라미터로 들어온 id를 토대로
    // 유저 정보를 상세보기하는 메소드이다.
    public UserDTO selectOne(int id) {
        // UserMapper의 selectOne 메소드로 id를 리턴한다.
        return sqlSession.selectOne(NAMESPACE + ".selectOne", id);
    }

}
