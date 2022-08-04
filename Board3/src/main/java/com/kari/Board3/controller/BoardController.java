package com.kari.Board3.controller;

import com.kari.Board3.model.BoardDTO;
import com.kari.Board3.model.UserDTO;
import com.kari.Board3.service.BoardService;
import com.kari.Board3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;
    @Autowired
    private UserService userService;

    @Autowired
    private ReplyController replyController;


    // 게시판에 쓰여진 글 보여줄 때 첫 번째 페이지로 가는 코드
    @RequestMapping("/selectAll")
    private String showFirstPage() {
        // 리턴을 통해 페이지 이동을 하는데
        // 이 경우 board 컨트롤러에 있는 showAll 메소드로 가라는 뜻이다.
        return "redirect:/board/showAll/1";
    }

    //{pageNo} 에 들어올 숫자에 따라 이동하는 페이지가 달라짐
    @RequestMapping("/showAll/{pageNo}")
    //(Model model)
    //Model model은 Model객체 생성을 위해 Spring에서 넘어온 값이며
    // showAll메소드에서 list, nicknameMap, pageNo, boardService.selectLastPage();
    // 객체를 board 폴더에 있는 showAll.jsp로 넘길때 담아주는 역할을 한다.
    private String showAll(Model model, HttpSession session, @PathVariable int pageNo) {

        //UserDTO클래스의 참조변수 logIn을 선언한 다음
        //세션에 저장되어있는 logIn객체를 session클래스의 getAttribute메소드로 가져와
        //(UserDTO)로 형변환해서 초기화 해주는 코드이다.
        // 형변환 해주는 이유: session에 저장된 객체는 Object 타입이기 때문
        UserDTO logIn = (UserDTO) session.getAttribute("logIn");

        // 로그인 객체에 값이 들어있는지 확인하여
        // 만약 null이면 인덱스 페이지로 리턴하는 코드이다.
        if( logIn == null) {
            return "redirect:/";
        }

        // BoardDTO 타입의 객체들이 담긴 list를 생성하여
        // 거기에 boardService의 selectAll 메소드를 실행시킨 값을 담는다.
        // 이 경우 파라미터로 들어온 pageNo를 넘겨주어
        // 특정 로우부터 특정 갯수의 행을 모두 찾아 리스트에 담는다는 뜻이다.
        // 파라미터로 들어온 pageNo는 showAll.jsp에서 받아오며
        // 자료형은 int 타입이다.
        List<BoardDTO> list = boardService.selectAll(pageNo);

        // 작성자 닉네임 보여주려고 쓰는 코드
        // 해쉬맵은 key:value 형태로 담기기 때문에
        // Integer(= 작성자의 아이디)가 key, String(= 작성자의 닉네임)이 value가 된다.
        HashMap<Integer, String> nicknameMap = new HashMap<>();
        // boardDTO 타입에 리스트에 for문을 통해 작성자의 아이디와 닉네임을 담는다.
        for (BoardDTO b : list) {
            nicknameMap.put(b.getWriterId(), userService.selectOne(b.getWriterId()).getNickname());
        }

        // modeld은 중간 매개체 느낌 (디비-뷰 통신 가능)
        // 디비에 매핑 가능, 값 가져오고 넣는 게 가능 !
        // 모델은 key:value 형식으로 저장되기 때문에 "list"란 이름으로 list 객체를 저장하겠다는 뜻이다.
        model.addAttribute("list", list);
        model.addAttribute("nicknameMap", nicknameMap);
        model.addAttribute("currentPage", pageNo);
        // boardService의 selectLastPage()를 실행시킨 값을 담는다.
        // 담긴 값의 자료형은 int 이다.
        model.addAttribute("lastPageNo", boardService.selectLastPage());

        // showAll.jsp로 이동하는 코드이다.
        return "/board/showAll";
    }


    // board의 write 페이지로 이동하는 코드
    // Get 방식으로 경로를 받아온다.
    @RequestMapping(value = "/write", method = RequestMethod.GET)
    private String register() {
        return "/board/write";
    }

    // POST 타입으로 요청했을 때 이 메소드가 실행된다.
    // 파라미터로 받아오는 image의 자료형은 MultipartFile 이다.
    // BoardDTO 타입의 b는 write.jsp 에서 넘어올 파라미터이다.
    @RequestMapping(value = "/write", method = RequestMethod.POST)
    private String writeLogic(@RequestParam MultipartFile image,
                              BoardDTO b, HttpSession session) {

        //UserDTO클래스의 참조변수 logIn을 선언한 다음
        //세션에 저장되어있는 logIn객체를 session클래스의 getAttribute메소드로 가져와
        //(UserDTO)로 형변환해서 초기화 해주는 코드이다.
        // 형변환 해주는 이유: session에 저장된 객체는 Spring 타입이기 때문
        UserDTO logIn = (UserDTO) session.getAttribute("logIn");

        // 로그인 객체에 값이 들어있는지 확인하여
        // 만약 null이면 인덱스 페이지로 리턴하는 코드이다.
        if (logIn == null) {
            return "redirect:/";
        }

        // 이미지가 저장될 경로를 미리 지정해준다.
        String path = "/Applications/Project_Spring/Board3/src/main/webapp/static/img/";

        //BoardDTO 객체에 값에
        // 로그인 세션에 들어있는 객체의 Id값을 세팅해준다.
        b.setWriterId(logIn.getId());
        // write.jsp 에서 작성한 제목을 세팅해준다.
        b.setTitle(b.getTitle());
        // 파라미터의 이름이 같기 때문에 setContent에 값을 세팅할 수 있다.
        b.setContent(b.getContent());

        // image를 담기 위해 예외처리 해줄 코드이다.
        try {
            // 경로와 그 이미지의 이름을 더해 새로운 파일을 만들라는 코드이다.
            // transferTO 는 컴퓨터의 있는 파일을 프로그램으로 가져와 변환해주는 코드이다.
            image.transferTo(new File(path + image.getOriginalFilename()));

            // 에러가 나타나면 잡는 부분
            // 실행중에 발생하며 환경적으로 input 값이 잘못된 경우
            // 혹은 의도적으로 프로그래머가 잡아내기 위한 조건들에 부합할 때 발생한다.
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 이미지가 올라왔을 때만 세팅해주기 위한 코드
        // 이미지가 null이 아니면
        // 이미지의 원래 이름을 저장한다.
        if(image != null) {
            b.setImageFileName(image.getOriginalFilename());
        }

        // b에 값이 다 담겼으니
        // boardSerivce의 insert 메소드에 파라미터로 보낸다.
        boardService.insert(b);

        // board 컨트롤러의 selectAll()를 실행하라는 뜻
        return "redirect:/board/selectAll";

    }

    // showAll.jsp에서 제목을 클릭함으로써
    // 파라미터로 들어온 id를 통해 글 상세보기를 하는 메소드이다.
    // model 객체에 담아 뷰로 보여주기 위해
    // String에서 파라미터로 받아온다.
    @RequestMapping("/showOne/{id}")
    public String selectOne(@PathVariable int id, HttpSession session, Model model) {

        // 로그인이 제대로 되어있는지 확인하는 코드
        // 로그인의 값이 null이면 index 페이지로 이동한다.
        if (session.getAttribute("logIn") == null) {
            return "redirect:/";
        }

        // board b 객체에 boardService.selectOne() 메소드를 실행한 값을 담아준다.
        // 파라미터로 들어온 id는 int 자료형의 boardId이다.
        BoardDTO b = boardService.selectOne(id);

        // 만약 b에 들어있는 값이 null이면
        // showAll.jsp로 이동하는 코드이다.
        if (b == null) {
            return "redirect:/board/showAll";
        }

        // model 객체에 b(= 게시물 정보)와 작성자의 닉네임을 담아준다.
        model.addAttribute("b", b);
        model.addAttribute("nickname", userService.selectOne(b.getWriterId()).getNickname());

        UserDTO logIn = (UserDTO) session.getAttribute("logIn");
        model.addAttribute("logInId", logIn.getId());

        // replyController의 showAll()을 실행한 값을 보여준다.
        // 파라미터로 들어온 id(= boardId)를 토대로
        // 그 게시물에 작성된 댓글을 보여주는 코드이다.
        replyController.showAll(id, model);

        // showOne.jsp 페이지로 이동한다.
        return "board/showOne";
    }


    // 게시물을 지정한 갯수만큼 추가하기 위한 메소드
    @RequestMapping("/init")
    public String insertAll() {
        // 73개 만큼 게시물을 추가한다.
        for (int i = 1; i <= 73; i++) {
            BoardDTO b = new BoardDTO();
            // 작성자의 아이디를 설정해주는 코드
            b.setWriterId(6);
            b.setTitle("제목" + i);
            b.setContent("내용" + i + "입니다 ! !");
            b.setViews(0);
            b.setGood(0);

            // boardService.init 메소드에 b를 담아 실행시킨다.
            boardService.init(b);
        }
        return "redirect:/";
    }

}
