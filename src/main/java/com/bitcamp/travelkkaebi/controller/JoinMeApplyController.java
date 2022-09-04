package com.bitcamp.travelkkaebi.controller;

import com.bitcamp.travelkkaebi.dto.JoinMeApplyResponseDTO;
import com.bitcamp.travelkkaebi.model.JoinMeApplyDTO;
import com.bitcamp.travelkkaebi.service.JoinMeApplyService;
import com.bitcamp.travelkkaebi.service.JoinMeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/joinmeapply")
@RequiredArgsConstructor
public class JoinMeApplyController {
    private final JoinMeApplyService joinMeApplyService;

    @PostMapping("/insert")
    public ResponseEntity<Boolean> insert(@RequestBody JoinMeApplyDTO joinMeApplyDTO,
                                          @AuthenticationPrincipal String userId){
        try{
            return new ResponseEntity<>(joinMeApplyService.insert(joinMeApplyDTO, Integer.parseInt(userId)), HttpStatus.OK);
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //본인이 신청한 신청서들
    @GetMapping("/selectall/byuserid")
    public ResponseEntity<List<JoinMeApplyResponseDTO>> selectAllByUserId(
            @RequestParam int pageNo,
            @AuthenticationPrincipal String userId){
        try{
            return new ResponseEntity<>(joinMeApplyService.selectAllByUserId(pageNo, Integer.parseInt(userId)), HttpStatus.OK);
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}