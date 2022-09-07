package com.bitcamp.travelkkaebi.controller;

import com.bitcamp.travelkkaebi.model.TravelMapDTO;
import com.bitcamp.travelkkaebi.service.TravelMapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/map")
public class TravelMapController {
    private final TravelMapService travelMapService;

    @PostMapping("/selectall")
    public ResponseEntity<List> selectAll(
            @RequestBody TravelMapDTO travelMapDTO) {
        try {
            return new ResponseEntity<>(travelMapService.selectAll(travelMapDTO), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<Boolean> insert(
            @RequestBody TravelMapDTO travelMapDTO,
            @AuthenticationPrincipal String userId) {
        try {
            return new ResponseEntity<>(travelMapService.insert(travelMapDTO, Integer.parseInt(userId)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Boolean> update(
            @RequestBody TravelMapDTO travelMapDTO,
            @AuthenticationPrincipal String userId) {
        try {
            return new ResponseEntity<>(travelMapService.update(travelMapDTO, Integer.parseInt(userId)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> delete(
            @RequestParam int travelMapId,
            @AuthenticationPrincipal String userId) {
        try {
            return new ResponseEntity<>(travelMapService.delete(travelMapId, Integer.parseInt(userId)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
