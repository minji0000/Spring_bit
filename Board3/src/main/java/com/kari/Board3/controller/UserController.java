package com.kari.Board3.controller;

import com.kari.Board3.model.UserDTO;
import com.kari.Board3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    // logIn 메소드를 POST로 요청하면 실행된다.
    // 로그인을 하는 메소드이다.
    @RequestMapping(value = "/logIn", method = RequestMethod.POST)
    public String logIn(String username, String password, HttpSession session) {
        UserDTO u = new UserDTO();
        // 파라미터로 들어온 String 타입의 username과 password를 세팅해준다.
        // 이 파라미터는 index 페이지에서 받오온다.
        u.setUsername(username);
        // password를 암호화 하기 위한 코드
        u.setPassword(convertSha2(password));

        // useService의 auth()메소드를 실행해
        // 아이디와 password가 일치하는지 검사한다.
        u = userService.auth(u);

        // 아이디가 null이 아니면
        // 세션에 logIn이라는 이름으로 u 객체를 세팅한다.
        if(u != null) {
            session.setAttribute("logIn", u);

            // 게시판으로 이동하는 코드
            // board컨트롤러의 selectAll 메소드를 실행시킨다.
            return "redirect:/board/selectAll";
        }

        // 로그인 객체에 값이 들어있지 않으면
        // 인덱스 페이지로 리턴한다.
        return "redirect:/";
    }

    // index 페이지에서 파라미터로 들어온
    // password를 암호화 해주는 메소드이다.
    private String convertSha2(String password) {
        String converted = null;
        StringBuilder builder = null;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // 암호화해주는 클래스를 통해 암호화를 진행한다.
            byte[] hash = md.digest(password.getBytes("UTF-8"));
            builder = new StringBuilder();

            for(int i = 0; i <hash.length; i++) {
                builder.append(String.format("%02x", 255 & hash[i]));
            }

            converted = builder.toString();

            // 에러가 발생하면 그 값을 리턴해주는 메소드
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return converted;
    }

    // 회원가입 페이지로 이동시켜주는 메소드이다.
    @RequestMapping("/register")
    private String register() {
        return "user/register";
    }

    // POST 방식으로 /register 경로로 요청하면
    // 이 메소드를 실행시킨다.
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    // 이 파라미터는 board 폴더의 register.jsp에서 받아온다.
    private String registerLogic(UserDTO u) {
        // password를 암호화하기 위해
        // userController의 convertSha2 메소드를 실행시킨 값을 세팅해준다.
        u.setPassword(convertSha2(u.getPassword()));

        // 비밀번호 암호화까지 끝난 u 객체를 userService의 register()로 리턴한다.
        userService.register(u);

        // 인덱스 페이지로 이동한다.
        return "redirect:/";
    }



}
