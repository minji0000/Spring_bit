package com.bitcamp.Board.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.bitcamp.Board.model.UserDTO;

//디비랑 직접적인 연결을 위해서
@Repository
// 이건 서비스야라고 외부에 알려주기 위해서
@Service
//디비와 통신해서 결과를 UserDTO 타입으로 만들어보내주는 역할
public class UserService {
	
	private final String NAMESPACE = "mapper.UserMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<UserDTO> selectAll() {
		return sqlSession.selectList(NAMESPACE + ".selectAll");
	}
	
	public UserDTO auth(UserDTO u) {
		return sqlSession.selectOne(NAMESPACE+ ".auth", u);
	}
	
	public void register(UserDTO u) {
		sqlSession.insert(NAMESPACE+ ".register", u);
		System.out.println("실행됨");
	}
	
	public UserDTO selectOne(int id) {
		return sqlSession.selectOne(NAMESPACE + ".selectOne", id);
	}

}
