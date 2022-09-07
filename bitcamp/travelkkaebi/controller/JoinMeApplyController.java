package com.bitcamp.travelkkaebi.controller;

import com.bitcamp.travelkkaebi.dto.JoinMeApplyResponseDTO;
import com.bitcamp.travelkkaebi.dto.ListResponseDTO;
import com.bitcamp.travelkkaebi.model.JoinMeApplyDTO;
import com.bitcamp.travelkkaebi.service.JoinMeApplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/joinmeapply")
@RequiredArgsConstructor
public class JoinMeApplyController {
    private final JoinMeApplyService joinMeApplyService;

    //로그인한 유저가 신청한 신청서들
    @GetMapping("/selectall/byuserid")
    public ResponseEntity<ListResponseDTO> selectAllByUserId(
            @RequestParam int pageNo,
            @AuthenticationPrincipal String userId) {
        try {
            return new ResponseEntity<>(joinMeApplyService.selectAllByUserId(pageNo, Integer.parseInt(userId)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    //내가 작성한 같이가요 글에 신청한 신청서들
    @GetMapping("/selectall/bywriterid")
    public ResponseEntity<ListResponseDTO> selectAllByWriterId(
            @RequestParam int pageNo,
            @AuthenticationPrincipal String userId) {
        try {
            return new ResponseEntity<>(joinMeApplyService.selectAllByWriterId(pageNo, Integer.parseInt(userId)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/selectone")
    public ResponseEntity<JoinMeApplyResponseDTO> selectOne(
            @RequestParam int joinMeApplyId) {
        try {
            return new ResponseEntity<>(joinMeApplyService.selectOne(joinMeApplyId), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    //신청서 작성
    @PostMapping("/insert")
    public ResponseEntity<Boolean> insert(
            @RequestBody JoinMeApplyDTO joinMeApplyDTO,
            @AuthenticationPrincipal String userId) {
        try {
            return new ResponseEntity<>(joinMeApplyService.insert(joinMeApplyDTO, Integer.parseInt(userId)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    //게시물 작성자가 신청서 채택하는 메소드
    @PutMapping("/selected")
    public ResponseEntity<Boolean> setSelectedTrue(
            @RequestParam int joinMeApplyId,
            @AuthenticationPrincipal String userId) {
        try {
            return new ResponseEntity<>(joinMeApplyService.setSelectedTrue(joinMeApplyId, Integer.parseInt(userId)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}