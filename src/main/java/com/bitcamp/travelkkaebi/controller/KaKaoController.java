package com.bitcamp.travelkkaebi.controller;

import com.bitcamp.travelkkaebi.dto.LogInDTO;
import com.bitcamp.travelkkaebi.service.KaKaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/travelkkaebi")
public class KaKaoController {

    private final KaKaoService kaKaoService;

    @GetMapping("/auth/kakao")
    public ResponseEntity<LogInDTO> oauthKakao(@RequestParam(value = "code", required = false) String authorizeCode) {
        return ResponseEntity.ok().body(kaKaoService.kaKaoAuth(authorizeCode));
    }
}
