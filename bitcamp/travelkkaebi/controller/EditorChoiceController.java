package com.bitcamp.travelkkaebi.controller;

import com.bitcamp.travelkkaebi.dto.EditorChoiceResponseDTO;
import com.bitcamp.travelkkaebi.model.EditorChoiceDTO;
import com.bitcamp.travelkkaebi.service.EditorChoiceService;
import com.bitcamp.travelkkaebi.service.LikeOrDislikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/editorchoice")
@RequiredArgsConstructor
public class EditorChoiceController {

    private final EditorChoiceService editorChoiceService;

    /**
     * 게시글 작성
     */
    @PostMapping("/write")
    public ResponseEntity write(@RequestBody EditorChoiceDTO editorChoiceDTO, @AuthenticationPrincipal String userId) {

       try {
            return new ResponseEntity(editorChoiceService.write(editorChoiceDTO,
                    Integer.parseInt(userId)), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 게시글 수정
     */
    @PutMapping("/update")
    private ResponseEntity update(@RequestBody EditorChoiceDTO editorChoiceDTO, @AuthenticationPrincipal String userId) {
        try {
            return new ResponseEntity(editorChoiceService.update(editorChoiceDTO,
                    Integer.parseInt(userId)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 게시글 삭제
     */
    @DeleteMapping("/delete")
    private ResponseEntity delete(@RequestBody EditorChoiceDTO editorChoiceDTO, @AuthenticationPrincipal String userId) {
        try {
            return new ResponseEntity(editorChoiceService.delete(editorChoiceDTO,
                    Integer.parseInt(userId)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 게시글 리스트
     */
    @GetMapping("/selectallbypage")
    private ResponseEntity selectAllByPage(@RequestParam int pageNo) {

        try {
            return new ResponseEntity(editorChoiceService.selectAllByPage(pageNo), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 게시글 리스트 (신규)
     */
    @GetMapping("/selectallnew")
    private ResponseEntity selectAllNew() {
        try {
            return new ResponseEntity(editorChoiceService.selectAllNew(), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 게시글 리스트 (추천)

    @GetMapping("/selectallgood")
    private ResponseEntity selectAllGood(@RequestParam int pageNo) {
        List<EditorChoiceResponseDTO> list;

        try {
            list = likeOrDislikeService.getBoardIdListMostLiked(3, 6);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return new ResponseEntity(list, HttpStatus.OK);
    }
     */


    /**
     * 게시글 상세보기
     */
    @GetMapping("/selectone")
    private ResponseEntity selectOne(@RequestParam int editorChoiceId) {

        try {
            return new ResponseEntity(editorChoiceService.selectOne(editorChoiceId), HttpStatus.OK);

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
            return new ResponseEntity(editorChoiceService.count(), HttpStatus.OK);

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
            return new ResponseEntity(editorChoiceService.searchByTitle(title), HttpStatus.OK);

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
            return new ResponseEntity(editorChoiceService.searchByContent(content), HttpStatus.OK);

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
            return new ResponseEntity(editorChoiceService.searchByWriter(writer), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * (지역) 키워드로 검색
     */

    @GetMapping("keywordbyregion")
    private ResponseEntity keywordByRegion (@RequestParam("region") String region) {

        try {
            return new ResponseEntity(editorChoiceService.keywordByRegion(region), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
