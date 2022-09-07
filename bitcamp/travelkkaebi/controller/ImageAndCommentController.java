package com.bitcamp.travelkkaebi.controller;

import com.bitcamp.travelkkaebi.model.ImageAndCommentDTO;
import com.bitcamp.travelkkaebi.service.ImageAndCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/imageandcomment")
public class ImageAndCommentController {

    private final ImageAndCommentService imageAndCommentService;

    /**
     * 사진 업로드 No
     */

    @PostMapping("/insert")
    public ResponseEntity<Boolean> insert(
            @RequestPart(value = "image") List<MultipartFile> imageList,
            @RequestPart(value = "comment") List<ImageAndCommentDTO> commentList,
            @AuthenticationPrincipal String userId) {
        System.out.println("imageAndComment 컨트롤러 도착");
        try {
            return new ResponseEntity<>(imageAndCommentService.insert(imageList, commentList,
                    Integer.parseInt(userId)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/test")
    public void test(@RequestPart List<MultipartFile> imageList,
                     @AuthenticationPrincipal String userId) {
        System.out.println("사진 테스트 컨트롤러");
        System.out.println("imageList.size() = " + imageList.size());
        System.out.println(imageList.get(0).getOriginalFilename());
        System.out.println(imageList.get(1).getOriginalFilename());
        try {
            imageAndCommentService.test(imageList, Integer.parseInt(userId));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 사진 및 코멘트 수정
     */
    @PutMapping("/update")
    public ResponseEntity update(@RequestPart(value = "image") List<MultipartFile> imageList,
           @RequestPart(value = "comment") List<ImageAndCommentDTO> commentList,
           @AuthenticationPrincipal String userId) {

        try {
            return new ResponseEntity<>(imageAndCommentService.update(imageList, commentList,
                    Integer.parseInt(userId)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

    }

    /**
     * 사진 및 코멘트 삭제
     */
    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestParam List<ImageAndCommentDTO> imageIdList, @AuthenticationPrincipal String userId) {
        try {
            return new ResponseEntity(imageAndCommentService.delete(imageIdList, Integer.parseInt(userId)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 게시글 상세보기
     */
    @GetMapping("/selectall")
    public ResponseEntity<List> selectAll (@RequestBody ImageAndCommentDTO imageAndCommentDTO) {
        try {
            return new ResponseEntity<>(imageAndCommentService.selectAll(imageAndCommentDTO), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

    }

    /**
     * 에디터 게시물 리스트 (신규)
     */
   // @GetMapping("/selectnew")
   // public ResponseEntity<List> selectMain (int pageNo)

    /**
     * 에디터 게시물 리스트 (추천)
     */



















}
