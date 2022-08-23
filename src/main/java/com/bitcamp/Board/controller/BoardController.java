package com.bitcamp.Board.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bitcamp.Board.model.BoardDTO;
import com.bitcamp.Board.model.UserDTO;
import com.bitcamp.Board.service.BoardService;
import com.bitcamp.Board.service.UserService;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	@Autowired
	private UserService userService;

	@RequestMapping("/showAll")
	public String showFirstPage() {
		// url로 이동
		return "redirect:/board/showAll/1";
	}

	@RequestMapping("/showAll/{pageNo}")
	public String showAll(Model model, HttpSession session, @PathVariable int pageNo) {

		UserDTO logIn = (UserDTO) session.getAttribute("logIn");

		if (logIn == null) {
			return "redirect:/";
		}

		List<BoardDTO> list = boardService.selectAll(pageNo);

		/*
		// showAll에서 작성자 닉네임 주르륵 띄워주기
		HashMap<Integer, String> nicknameMap = new HashMap<>();
		for (BoardDTO b : list) {
			nicknameMap.put(b.getWriterId(), userService.selectOne(b.getWriterId()).getNickname());
		}

		 */

		model.addAttribute("list", list);
		//model.addAttribute("nicknameMap", nicknameMap);
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("lastPageNo", boardService.selectLastPage());

		return "/board/showAll";
	}

	@RequestMapping("/selectOne/{id}")
	public String selectOne(@PathVariable int id, HttpSession session, Model model) {

		// 로그인이 제대로 되어있는지
		if (session.getAttribute("logIn") == null) {
			return "redirect:/";
		}

		// 글도 존재하는 번호인지
		BoardDTO b = boardService.selectOne(id);
		if (b == null) {
			return "redirect:/board/showAll";
		}

		model.addAttribute("b", b);
		//model.addAttribute("nickname", userService.selectOne(b.getWriterId()).getNickname());
		
		UserDTO logIn = (UserDTO) session.getAttribute("logIn");
		//model.addAttribute("logInId", logIn.getId());

		return "board/showOne";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(@RequestParam MultipartFile image) {
		System.out.println(image.getOriginalFilename());
		// 서버 어디에 이미지가 업로드가 될지 결정하는 경로
		String path = "/Users/mingdi/spring_image/";
		
		try {
			File temp = new File(path);
			//디렉토리를 만들어
			temp.mkdirs();
			// 해당하는 패스 안에 우리가 업로드 한 파일과 동일한 이름의 파일 전송해
			image.transferTo(new File(path + image.getOriginalFilename()));
			
		} catch (IllegalStateException | IOException e) {
			// path가 올바르지 않을 경우
			e.printStackTrace();
		}

		return "";
	}

	// 게시글 작성하는 메소드
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write (String title, String content, HttpSession session) {
		BoardDTO b = new BoardDTO();
		UserDTO logIn = (UserDTO) session.getAttribute("logIn");
		b.setTitle(title);
		b.setContent(content);
		b.setWriterId(logIn.getId());

		boardService.insert(b);

		return "redirect:/board/showAll";
	}

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write () {
		return "board/write";
	}


	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	// 모델은 HashMap 형태로 키값과 밸류 값처럼 사용할 수 있다.
	public String update(@PathVariable int id, Model model) {
		// 키 밸류로 모델에 저장함
		model.addAttribute("id", id);
		return "/board/update";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String update (String title, String content, @PathVariable int id) {
		BoardDTO b = new BoardDTO();
		b.setTitle(title);
		b.setContent(content);
		b.setId(id);

		boardService.update(b);

		return "redirect:/board/selectOne/" +id;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable int id) {

		boardService.delete(id);

		return "redirect:/board/showAll";
	}












	

}
