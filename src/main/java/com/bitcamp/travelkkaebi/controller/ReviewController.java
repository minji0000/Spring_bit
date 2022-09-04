package com.bitcamp.travelkkaebi.controller;
// 후기 게시판


import com.bitcamp.travelkkaebi.dto.ReviewResponseDTO;
import com.bitcamp.travelkkaebi.model.ReviewDTO;
import com.bitcamp.travelkkaebi.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    // GET -> RequestParam
    // POST -> RequestBody

    /**
     * 게시글 작성 Ok
     */
    @PostMapping("/write")
    public ResponseEntity write(@RequestBody ReviewDTO reviewDTO,
                                @AuthenticationPrincipal String userId) {
        try {
            int reviewId = reviewService.writeReview(reviewDTO, Integer.parseInt(userId));
            return new ResponseEntity(reviewId, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 게시글 수정 Ok
     */
    @PutMapping("/update")
    private ResponseEntity update(@RequestBody ReviewDTO reviewDTO, @AuthenticationPrincipal String userId) {
        try {
            int updatedId = reviewService.update(reviewDTO, Integer.parseInt(userId));
            return new ResponseEntity(updatedId, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 게시글 삭제 OK
     */
    @DeleteMapping("/delete")
    private ResponseEntity delete(@RequestBody ReviewDTO review, @AuthenticationPrincipal String userId) {
        System.out.println("게시글 삭제 컨트롤러 도착");
        try {
            int deletedId = reviewService.delete(review, Integer.parseInt(userId));
            return new ResponseEntity(deletedId, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 게시글 리스트 출력
     */
    @GetMapping("/selectallbypage")
    private ResponseEntity selectAll(@RequestParam int pageNo) {
        List<ReviewResponseDTO> reviewList;
        System.out.println("게시글 리스트 컨트롤러 들어왔어요");

        try {
            reviewList = reviewService.selectAllByPage(pageNo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return new ResponseEntity(reviewList, HttpStatus.OK);
    }

    /**
     * 게시글 상세보기
     */
    @GetMapping("/selectone")
    private ResponseEntity selectOne(@RequestParam int reviewId) {
        ReviewResponseDTO review;
        System.out.println("게시물 상세보기 컨트롤러 도착");

        try {
            review = reviewService.selectOne(reviewId);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return new ResponseEntity(review, HttpStatus.OK);
    }

    /**
     * 게시물 갯수 반환
     */
    @GetMapping("/count")
    private ResponseEntity count() {
        int reviewCount;
        try {
            reviewCount = reviewService.count();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return new ResponseEntity(reviewCount, HttpStatus.OK);
    }

    /**
     * 특정 제목으로 검색
     */
    @GetMapping("/searchbytitle")
    private ResponseEntity searchByTitle(@RequestParam("title") String title) {
        List<ReviewResponseDTO> titleList;

        try {
            titleList = reviewService.searchByTitle(title);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return new ResponseEntity(titleList, HttpStatus.OK);
    }

    /**
     * 특정 내용으로 검색
     */
    @GetMapping("/searchbycontent")
    private ResponseEntity searchByContent(@RequestParam("content") String content) {
        List<ReviewResponseDTO> contentList;

        try {
            contentList = reviewService.searchByContent(content);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return new ResponseEntity(contentList, HttpStatus.OK);
    }

    /**
     * 특정 작성자로 검색
     */
    @GetMapping("/searchbywriter")
    private ResponseEntity searchByWriter(@RequestParam("writer") String writer) {
        List<ReviewResponseDTO> writerList;

        try {
            writerList = reviewService.searchByWriter(writer);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return new ResponseEntity(writerList, HttpStatus.OK);
    }

    /**
     * (지역) 키워드로 검색
     */
    @GetMapping("/keywordbyregion")
    private ResponseEntity keywordByRegion (@RequestParam("region") String region) {
        List <ReviewResponseDTO> regionList;

        try {
            regionList = reviewService.keywordByRegion(region);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return new ResponseEntity(regionList, HttpStatus.OK);
    }

}