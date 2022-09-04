package com.bitcamp.travelkkaebi.controller;

import com.bitcamp.travelkkaebi.dto.LikeOrDislikeResponseDTO;
import com.bitcamp.travelkkaebi.model.LikeOrDislikeDTO;
import com.bitcamp.travelkkaebi.service.LikeOrDislikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likeordislike")
@RequiredArgsConstructor
public class LikeOrDislikeController {
    private final LikeOrDislikeService likeOrDislikeService;

    @PostMapping("/selectone")
    public ResponseEntity<LikeOrDislikeResponseDTO> selectOne(
            @RequestBody LikeOrDislikeDTO likeOrDislikeDTO,
            @AuthenticationPrincipal String userId) {
        try {
            return new ResponseEntity<>(likeOrDislikeService.selectOne(likeOrDislikeDTO,
                    Integer.parseInt(userId)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PutMapping("/clicklike")
    public ResponseEntity<LikeOrDislikeResponseDTO> clickLike(
            @RequestParam int likeOrDislikeId,
            @AuthenticationPrincipal String userId) {
        try {
            return new ResponseEntity<>(likeOrDislikeService.clickLike(likeOrDislikeId,
                    Integer.parseInt(userId)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PutMapping("/clickdislike")
    public ResponseEntity<LikeOrDislikeResponseDTO> clickDislike(
            @RequestParam int likeOrDislikeId,
            @AuthenticationPrincipal String userId) {
        try {
            return new ResponseEntity<>(likeOrDislikeService.clickDislike(
                    likeOrDislikeId, Integer.parseInt(userId)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
