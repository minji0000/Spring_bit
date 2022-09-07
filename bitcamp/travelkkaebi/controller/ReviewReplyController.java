package com.bitcamp.travelkkaebi.controller;

import com.bitcamp.travelkkaebi.model.ReviewDTO;
import com.bitcamp.travelkkaebi.model.ReviewReplyDTO;
import com.bitcamp.travelkkaebi.service.ReviewReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/review/reply")
public class ReviewReplyController {

    private final ReviewReplyService reviewReplyService;

    /**
     * 댓글 작성
     */
    @PostMapping("/write")
    public ResponseEntity replyWrite(@RequestPart ReviewReplyDTO reviewReplyDTO, @RequestPart ReviewDTO reviewDTO,
                                     @AuthenticationPrincipal String userId) {
        try {
            return new ResponseEntity(reviewReplyService.writeReply(reviewReplyDTO, reviewDTO,
                    Integer.parseInt(userId)), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 댓글 수정
     */
    @PutMapping("/update")
    private ResponseEntity replyUpdate(@RequestBody ReviewReplyDTO reviewReplyDTO, @AuthenticationPrincipal String userId) {
        try {
            return new ResponseEntity(reviewReplyService.update(reviewReplyDTO,
                    Integer.parseInt(userId)), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 댓글 삭제
     */
    @DeleteMapping("/delete")
    private ResponseEntity replyDelete(@RequestBody ReviewReplyDTO reply, @AuthenticationPrincipal String userId) {
        try {
            return new ResponseEntity(reviewReplyService.delete(reply,
                    Integer.parseInt(userId)), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 댓글 조회 (특정 게시물에 달린)
     */
    @GetMapping("/selectbyreview")
    private ResponseEntity selectByReview(@RequestParam int reviewId) {

        try {
            return new ResponseEntity(reviewReplyService.selectOne(reviewId), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
