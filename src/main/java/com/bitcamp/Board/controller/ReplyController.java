package com.bitcamp.Board.controller;


import com.bitcamp.Board.model.ReplyDTO;
import com.bitcamp.Board.model.UserDTO;
import com.bitcamp.Board.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/reply")
public class ReplyController {
    @Autowired
    private ReplyService replyService;

    @RequestMapping(value = "/write/{id}" , method = RequestMethod.POST)
    public String write(Model model, String content, HttpSession session, @PathVariable int id) {
        System.out.println("실행됨");
        ReplyDTO r = new ReplyDTO();
        //  주의할 점은 반환되는 값이 Object형이기 때문에 반드시 적절한 형변환을 해야한다.
        UserDTO logIn = (UserDTO) session.getAttribute("logIn");

        r.setContent(content);
        r.setWriterId(logIn.getId());
        r.setBoardId(id);

        replyService.insert(r);

        model.addAttribute("replyList", replyService.selectAll(id));

        return "redirect:/board/selectOne/" + id;

    }
}
