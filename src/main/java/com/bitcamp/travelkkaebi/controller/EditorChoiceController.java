package com.bitcamp.travelkkaebi.controller;

import com.bitcamp.travelkkaebi.dto.EditorChoiceResponseDTO;
import com.bitcamp.travelkkaebi.model.EditorChoiceDTO;
import com.bitcamp.travelkkaebi.service.EditorChoiceService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        System.out.println("게시글 작성 컨트롤러");
       try {
            int editorId = editorChoiceService.write(editorChoiceDTO, Integer.parseInt(userId));
            return new ResponseEntity(editorId, HttpStatus.OK);

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
            int updatedId = editorChoiceService.update(editorChoiceDTO, Integer.parseInt(userId));
            return new ResponseEntity(updatedId, HttpStatus.OK);
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
            int deletedId = editorChoiceService.delete(editorChoiceDTO, Integer.parseInt(userId));
            return new ResponseEntity(deletedId, HttpStatus.OK);
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
        List<EditorChoiceResponseDTO> list;

        try {
            list = editorChoiceService.selectAllByPage(pageNo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return new ResponseEntity(list, HttpStatus.OK);
    }


    /**
     * 게시글 상세보기
     */
    @GetMapping("/selectone")
    private ResponseEntity selectOne(@RequestParam int editorChoiceId) {
        EditorChoiceResponseDTO editorChoiceResponseDTO;

        try {
            editorChoiceResponseDTO = editorChoiceService.selectOne(editorChoiceId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return new ResponseEntity(editorChoiceResponseDTO, HttpStatus.OK);
    }

    /**
     * 게시물 갯수 반환
     */
    @GetMapping("/count")
    private ResponseEntity count() {
        int editorCount;
        try {
            editorCount = editorChoiceService.count();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return new ResponseEntity(editorCount, HttpStatus.OK);
    }

    /**
     * 특정 제목으로 검색
     */
    @GetMapping("/searchbytitle")
    private ResponseEntity searchByTitle(@RequestParam("title") String title) {
        List<EditorChoiceResponseDTO> titleList;

        try {
            titleList = editorChoiceService.searchByTitle(title);
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

        List<EditorChoiceResponseDTO> contentList;

        try {
            contentList = editorChoiceService.searchByContent(content);
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

        List<EditorChoiceResponseDTO> writerList;

        try {
            writerList = editorChoiceService.searchByWriter(writer);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return new ResponseEntity(writerList, HttpStatus.OK);
    }

    /**
     * (지역) 키워드로 검색
     */

    @GetMapping("keywordbyregion")
    private ResponseEntity keywordByRegion (@RequestParam("region") String region) {
        List <EditorChoiceResponseDTO> regionList;

        try {
            regionList = editorChoiceService.keywordByRegion(region);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return new ResponseEntity(regionList, HttpStatus.OK);
    }

}
