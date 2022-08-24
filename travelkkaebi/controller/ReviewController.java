package com.bitcamp.travelkkaebi.controller;
// 후기 게시판


import com.bitcamp.travelkkaebi.dto.LogInDTO;
import com.bitcamp.travelkkaebi.model.ReviewDTO;
import com.bitcamp.travelkkaebi.model.ReviewReplyDTO;
import com.bitcamp.travelkkaebi.service.LikeOrDislikeService;
import com.bitcamp.travelkkaebi.service.ReviewReplyService;
import com.bitcamp.travelkkaebi.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewReplyService reviewReplyService;

    private final LikeOrDislikeService likeOrDislikeService;

    // 후기 게시글 작성하는 메소드
    @PostMapping("/write")
    public void write(@RequestBody ReviewDTO reviewDTO, @AuthenticationPrincipal int userId) {
        System.out.println("컨트롤러");
        reviewService.writeReview(reviewDTO, userId);
    }

    // 후기 게시글 수정하는 메소드
    @PutMapping("/update")
    private void update(@RequestBody ReviewDTO reviewDTO) {
        reviewService.update(reviewDTO);
    }

    // 후기 게시글 삭제하는 메소드
    @DeleteMapping("/delete")
    private void delete(@RequestBody int reviewId) {
        reviewService.delete(reviewId);
    }


    // 후기 게시글 최신순으로 모두 보여주는 메소드
    @GetMapping("/selectAll")
    private List<ReviewDTO> selectAll() {
        List<ReviewDTO> reviewList = reviewService.selectAll();
        return reviewList;
    }





    // ----------------- 댓글 관련 메소드 -----------------------------

    // 후기 게시글에 댓글 작성하는 메소드
    @PostMapping ("/replywrite")
    public void replyWrite(@RequestBody ReviewReplyDTO reviewReplyDTO, @RequestBody ReviewDTO reviewDTO, @AuthenticationPrincipal int userId) {
        reviewReplyService.writeReply(reviewReplyDTO, reviewDTO, userId);
    }

    // 후기 게시글의 댓글 수정하는 메소드
    @PutMapping("/replyupdate")
    private void replyUpdate(@RequestBody ReviewReplyDTO reviewReplyDTO) {
        reviewReplyService.update(reviewReplyDTO);
    }

    // 후기 게시글의 댓글 삭제하는 메소드
    @DeleteMapping("/replydelete")
    private void replyDelete(@RequestBody int reviewReplyId) {
        reviewReplyService.delete(reviewReplyId);
    }



    // 후기 게시글 상세보기
    @GetMapping("/selectOne")
    private ReviewDTO selectOne(@RequestBody ReviewDTO reviewDTO, @AuthenticationPrincipal int userId) {
        // 조회수 올려주는 코드
        ReviewDTO review = reviewService.selectOne(reviewDTO, userId);
        return review;
    }

    // 후기 게시글에 달린 댓글 보기
    @GetMapping("/reply/selectOne")
    private List<ReviewReplyDTO> replySelectOne(@RequestBody int reviewId) {
        List<ReviewReplyDTO> list = reviewReplyService.selectOne(reviewId);
        return list;
    }

    // 후기 게시글에 달린 좋아요 상태 보기
    //@GetMapping("/review/status")
    @GetMapping("/selectOne")
    private HashMap<String, Boolean> reviewStatus(@RequestBody ReviewDTO reviewDTO) {
      HashMap<String, Boolean> likeMap = reviewService.ReviewStatus(reviewDTO);
      return likeMap;
    }




}
