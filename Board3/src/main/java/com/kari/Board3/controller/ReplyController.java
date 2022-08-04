package com.kari.Board3.controller;

import com.kari.Board3.model.ReplyDTO;
import com.kari.Board3.model.UserDTO;
import com.kari.Board3.service.ReplyService;
import com.kari.Board3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/reply")
public class ReplyController {
    @Autowired
    private ReplyService replyService;

    @Autowired
    private UserService userService;

    // write 메소드를 POST로 요청하면 실행된다.
    @RequestMapping(value = "/write/{id}" , method = RequestMethod.POST)
    public String write(Model model, String content, HttpSession session, @PathVariable int id) {

        ReplyDTO r = new ReplyDTO();
        //UserDTO클래스의 참조변수 logIn을 선언한 다음
        //세션에 저장되어있는 logIn객체를 session클래스의 getAttribute메소드로 가져와
        //(UserDTO)로 형변환해서 초기화 해주는 코드이다.
        //  주의할 점은 반환되는 값이 Object형이기 때문에 반드시 적절한 형변환을 해야한다.
        UserDTO logIn = (UserDTO) session.getAttribute("logIn");

        // 댓글을 작성하기 위해 showOne.jsp에서 넘어온 content, id를 세팅해준다.
        r.setContent(content);
        // 댓글의 작성자 아이디는 현재 로그인된 id이기 때문에 로그인의 아이디를 세팅해준다.
        r.setWriterId(logIn.getId());
        r.setBoardId(id);

        replyService.insert(r);

        //  (Model model)
        //  Model model은 Model객체 생성을 위해 Spring에서 넘어온 값이며
        // replySerive의 selectAll()를 실행시킨 replyList 값을
        // 객체를 board 폴더에 있는 showOne.jsp로 넘길때 담아주는 역할을 한다.
        model.addAttribute("replyList", replyService.selectAll(id));

        // 파라미터로 들어온 int 타입의 id를
        // 보드 컨트롤러의 showOne 메소드로 리턴해준다.
        // id는 (=boardI는)이다.
        return "redirect:/board/showOne/" + id;
    }

    @RequestMapping("/showAll")
    //  (Model model)
    //  Model model은 Model객체 생성을 위해 Spring에서 넘어온 값이며
    // replySerive의 selectAll()를 실행시킨 replyList 값을
    // 객체를 board 폴더에 있는 showOne.jsp로 넘길때 담아주는 역할을 한다.
    public String showAll(@PathVariable int id, Model model){

        // ReplyDTO 타입의 객체들이 담긴 list를 생성하여
        // 거기에 replyService의 selectAll 메소드를 실행시킨 값을 담는다.
        // 이 경우 파라미터로 들어온 id(=boardId)를 넘겨준다.
        // 파라미터로 들어온 id는 board 폴더의 showOne에서 들어온
        // int형 타입의 id이다.
        List<ReplyDTO> replyList = replyService.selectAll(id);

        // 작성자 닉네임 보여주려고 쓰는 코드
        // 해쉬맵은 key:value 형태로 담기기 때문에
        // Integer(= 작성자의 아이디)가 key, String(= 작성자의 닉네임)이 value가 된다.
        HashMap<Integer, String> nicknameMap = new HashMap<>();
        for (ReplyDTO r : replyList) {
            nicknameMap.put(r.getWriterId(), userService.selectOne(r.getWriterId()).getNickname());
        }

        // 모델은 key:value 형식으로 저장되기 때문에 "replylist"란 이름으로 replylist 객체를 저장하겠다는 뜻이다.
        model.addAttribute("replyList", replyList);
        model.addAttribute("nicknameMap", nicknameMap);

        // board 폴더의 showOne.jsp로 이동한다.
        return "/board/showOne";
    }


}
