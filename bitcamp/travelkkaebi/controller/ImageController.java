package com.bitcamp.travelkkaebi.controller;

import com.bitcamp.travelkkaebi.model.ImageDTO;
import com.bitcamp.travelkkaebi.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {
    private final ImageService imageService;

    @PostMapping("/selectall")
    public ResponseEntity<List> selectAll(@RequestBody ImageDTO imageDTO) {
        try {
            return new ResponseEntity<>(imageService.selectAll(imageDTO), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<Boolean> insert(
            @RequestPart(value = "file") List<MultipartFile> multipartFileList,
            @RequestPart(value = "imageDTO") List<ImageDTO> imageDTOList,
            @AuthenticationPrincipal String userId) {
        try {
            return new ResponseEntity<>(imageService.insert(multipartFileList, imageDTOList, Integer.parseInt(userId)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Boolean> update(
            @RequestPart(value = "file") List<MultipartFile> multipartFileList,
            @RequestPart(value = "imageDTO") List<ImageDTO> imageDTOList,
            @AuthenticationPrincipal String userId) {
        try {
            return new ResponseEntity<>(imageService.update(multipartFileList, imageDTOList, Integer.parseInt(userId)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> delete(
            @RequestParam List<Integer> imageIdList,
            @AuthenticationPrincipal String userId) {
        try {
            return new ResponseEntity<>(imageService.delete(imageIdList, Integer.parseInt(userId)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}