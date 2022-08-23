package com.bitcamp.Board.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.bitcamp.Board.model.BoardDTO;

// 디비와 가장 잘 통신하는 친구
@Repository
@Service
public class BoardService {
	private final String NAMESPACE = "mapper.BoardMapper";
	@Autowired
	// ?
	SqlSession sqlSession;
	private final int PAGE_SIZE = 15;
	
	// 디비에 저장된 게시글들을 담아줄 list
	public List<BoardDTO> selectAll(int pageNo) {
		
		HashMap<String, Integer> map = new HashMap<>();
		int startNum = (pageNo - 1) * PAGE_SIZE ;
		map.put("startNum", startNum);
		map.put("PAGE_SIZE", PAGE_SIZE);
		
		return sqlSession.selectList(NAMESPACE + ".selectAll", map);
	}
	
	public BoardDTO selectOne(int id) {
		return sqlSession.selectOne(NAMESPACE + ".selectOne", id);
	}
	
	public void insert(BoardDTO b) {
		sqlSession.insert(NAMESPACE + ".insert", b);	
	}
	
	public int selectLastPage() {
		int total = sqlSession.selectOne(NAMESPACE + ".count");
		
		if(total % PAGE_SIZE == 0) {
			return total / PAGE_SIZE;
		} else {
			
			return (total/ PAGE_SIZE) + 1;
		}
	}

	public void update(BoardDTO b) {
		sqlSession.update(NAMESPACE + ".update", b);
	}

	public void delete(int id) {
		sqlSession.delete(NAMESPACE + ".delete", id);
	}
	
}
