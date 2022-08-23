package com.bitcamp.Board.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bitcamp.Board.model.UserDTO;
import com.bitcamp.Board.service.UserService;

//스프링 프레임워크한테 이 클래스가 Controller라는 걸 알려줘
@Controller
//url 매핑
@RequestMapping("/user")
public class UserController {
	// 필요한 의존 객체의 "타입"에 해당하는 빈을 찾아 주입한다.
	// Bean이란 스프링 컨테이너가 관리하는 자 객체이다. (new 연산자로 생성 시 그 객체는 빈이 아님)
	// Autowired는 타입으로 의존성을 주입한다 
	//  ? ?? 어려웡,,
	@Autowired
	private UserService userService;
	
	// 요청 받을 url 설정
	@RequestMapping(value = "/selectAll")
	public String selectAll(Model model) {
		model.addAttribute("list", userService.selectAll());
		
		return "user/showAll";
	}

	/*
	@RequestMapping(value = "/logIn", method = RequestMethod.POST)	
	// 로그인 
	public String logIn(String username, String password, HttpSession session) {

		UserDTO u = new UserDTO();
		u.setUsername(username);
		u.setPassword(convertSha2(password));

		u = userService.auth(u);
		
		if(u != null) {
			//("저장하고 싶은 변수명", 저장 변수값) 여기엔 다 들어감
			// 이 브라우저 내장 메모리,, 20분 동안 
			session.setAttribute("logIn", u);
			
			return "redirect:/board/showAll";
		}
		
		System.out.println(u);
		//특정 경로(url)로 이동 시 redirect 사용, 특정 페이지(파일)를 로딩해야한다 그러면 안 써도 돼
		//안 쓰고 싶으면 index(로그인 페이지로 튕겨야하니까)라고 써주면 돼(근데 경로 다 써줘야함)
		
		return "redirect:/";
		}
	 */

	@RequestMapping("/selectOne/{id}")
	// ? 동일한 이름을 갖는 파라미터에 매핑된다는데 그럼 저렇게 들어오면 이 아래에 있는 메소드에 매핑된다는건가 ?
	public String selectOne(@PathVariable int id) {
		System.out.println(id);
		
		return "/index.jsp";	
	}
	
	private String convertSha2(String password) {
		String converted = null;
        StringBuilder builder = null;
		
		try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // 암호화해주는 클래스
            byte[] hash = md.digest(password.getBytes("UTF-8"));
            builder = new StringBuilder();

            for(int i = 0; i <hash.length; i++) {
                builder.append(String.format("%02x", 255 & hash[i]));
            }

            converted = builder.toString();

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    return converted;

	}

	@RequestMapping (value = "/register", method = RequestMethod.GET)
	public String register() {
		return "user/register";
	}

	/*
	// 필드명이랑 input 안에 이름이 같으면 알아서 들어감
	@RequestMapping (value = "/register", method = RequestMethod.POST)
	//객체를 파라미터로 넣어주면 스프링이 알아서 해줘
	public String registerLogic(UserDTO u) {

		u.setUsername(u.getUsername());
		u.setPassword(convertSha2(u.getPassword()));
		u.setNickname(u.getNickname());
		System.out.println(u.getUsername());
		userService.register(u);
		
		return "redirect:/";
}
		 */

	
	

	
}
