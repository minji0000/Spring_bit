package com.bitcamp.travelkkaebi.controller;

import com.bitcamp.travelkkaebi.service.PickMeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/travelkkaebi/pickme")
@RequiredArgsConstructor
public class PickMeController {

    private final PickMeService pickMeService;

    @GetMapping("/main")
    public ResponseEntity<Void> showPickMeList() {
        return ResponseEntity.ok().build();
    }

}
