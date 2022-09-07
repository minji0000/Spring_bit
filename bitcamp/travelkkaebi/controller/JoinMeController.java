package com.bitcamp.travelkkaebi.controller;

import com.bitcamp.travelkkaebi.dto.JoinMeOneDTO;
import com.bitcamp.travelkkaebi.dto.ListResponseDTO;
import com.bitcamp.travelkkaebi.model.JoinMeDTO;
import com.bitcamp.travelkkaebi.service.JoinMeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/joinme")
@RequiredArgsConstructor
public class JoinMeController {
    private final JoinMeService joinMeService;

    //pageNo에 페이지번호를 넣어서 보내주면 해당페이지의 게시물 20개를 리턴
    @GetMapping("/selectallbypage")
    public ResponseEntity<ListResponseDTO> selectAllByPage(
            @RequestParam int pageNo) {
        try {
            //해당 pageNo의 게시물 리스트를 리턴
            return new ResponseEntity<>(joinMeService.selectAllByPage(pageNo), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/selectallbypage/keyword")
    public ResponseEntity<ListResponseDTO> selectAllByPageAndKeyword(
            @RequestParam int pageNo,
            @RequestParam String keyword) {
        try {
            return new ResponseEntity<>(joinMeService.selectAllByPageAndKeyword(pageNo, keyword), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    //제목으로 검색
    @GetMapping("/selectallbypage/searchbytitle")
    public ResponseEntity<ListResponseDTO> selectAllByPageAndTitle(
            @RequestParam int pageNo,
            @RequestParam String searchword) {
        try {
            return new ResponseEntity<>(joinMeService.selectAllByPageAndTitle(pageNo, searchword), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    //닉네임으로 검색
    @GetMapping("/selectallbypage/searchbynickname")
    public ResponseEntity<ListResponseDTO> selectAllByPageAndNickname(
            @RequestParam int pageNo,
            @RequestParam String searchword) {
        try {
            return new ResponseEntity<>(joinMeService.selectAllByPageAndNickname(pageNo, searchword), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    //유저가 해당 게시물을 클릭해서 상세보기 했을 때 joinMeId를 받아서 Service로 넘겨준다.
    @GetMapping("/selectone")
    public ResponseEntity<JoinMeOneDTO> selectOne(@RequestParam int joinMeId) {
        try {
            return new ResponseEntity<>(joinMeService.selectOne(joinMeId), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    //유저가 글을 썼을 때 들어온 객체를 Service로 보내고 삽입된 객체 리턴
    @PostMapping("/insert")
    public ResponseEntity<JoinMeOneDTO> insert(@RequestBody JoinMeDTO joinMeDTO,
                                               @AuthenticationPrincipal String userId) {
        try {
            return new ResponseEntity<>(joinMeService.insert(joinMeDTO, Integer.parseInt(userId)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    //유저가 글을 수정했을 때 수정된 객체를 받아서 Service로 넘겨주고 수정된 객체 리턴
    @PutMapping("/update")
    public ResponseEntity<JoinMeOneDTO> update(
            @RequestBody JoinMeDTO joinMeDTO,
            @AuthenticationPrincipal String userId) {
        try {
            return new ResponseEntity<>(joinMeService.update(joinMeDTO, Integer.parseInt(userId)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    //유저가 글을 삭제했을 때 삭제할 글의 joinMeId를 받아서 Service로 넘겨준다.
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> delete(
            @RequestBody JoinMeDTO joinMeDTO,
            @AuthenticationPrincipal String userId) {
        try {
            return new ResponseEntity<>(joinMeService.delete(joinMeDTO, Integer.parseInt(userId)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}