package com.bitcamp.travelkkaebi.controller;
// 후기 게시판


import com.bitcamp.travelkkaebi.model.ReviewDTO;
import com.bitcamp.travelkkaebi.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


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
            return new ResponseEntity(reviewService.writeReview(reviewDTO,
                    Integer.parseInt(userId)), HttpStatus.OK);

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
            return new ResponseEntity(reviewService.update(reviewDTO, Integer.parseInt(userId)), HttpStatus.OK);
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
        try {
            return new ResponseEntity(reviewService.delete(review, Integer.parseInt(userId)), HttpStatus.OK);

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

        try {
            return new ResponseEntity(reviewService.selectAllByPage(pageNo), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 게시글 상세보기
     */
    @GetMapping("/selectone")
    private ResponseEntity selectOne(@RequestParam int reviewId) {

        try {
            return new ResponseEntity(reviewService.selectOne(reviewId), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 게시물 갯수 반환
     */
    @GetMapping("/count")
    private ResponseEntity count() {
        try {
            return new ResponseEntity(reviewService.count(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 특정 제목으로 검색
     */
    @GetMapping("/searchbytitle")
    private ResponseEntity searchByTitle(@RequestParam("title") String title) {

        try {
            return new ResponseEntity(reviewService.searchByTitle(title), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 특정 내용으로 검색
     */
    @GetMapping("/searchbycontent")
    private ResponseEntity searchByContent(@RequestParam("content") String content) {

        try {
            return new ResponseEntity(reviewService.searchByContent(content), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 특정 작성자로 검색
     */
    @GetMapping("/searchbywriter")
    private ResponseEntity searchByWriter(@RequestParam("writer") String writer) {
        try {
            return new ResponseEntity(reviewService.searchByWriter(writer), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * (지역) 키워드로 검색
     */
    @GetMapping("/keywordbyregion")
    private ResponseEntity keywordByRegion (@RequestParam("region") String region) {

        try {
            return new ResponseEntity(reviewService.keywordByRegion(region), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}