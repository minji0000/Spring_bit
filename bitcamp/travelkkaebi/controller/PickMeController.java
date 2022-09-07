package com.bitcamp.travelkkaebi.controller;

import com.bitcamp.travelkkaebi.dto.PickMeDTO;
import com.bitcamp.travelkkaebi.service.PickMeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/travelkkaebi")
@RequiredArgsConstructor
public class PickMeController {

    private final PickMeService pickMeService;

    private final int PAGE_SIZE = 20;

    /**
     * pickMe 게시글 20개씩 pagination return
     */
    @GetMapping("/pickme")
    public ResponseEntity<List<PickMeDTO>> showPickMeList(@PageableDefault(size = PAGE_SIZE) Pageable pageable) {
        return ResponseEntity.ok().body(pickMeService.findAll(pageable));
    }

    /**
     * pickMe write
     */
    @PostMapping("/pickme/write")
    public ResponseEntity<PickMeDTO> pickMeWrite(@AuthenticationPrincipal String userId, @RequestBody PickMeDTO pickMeDTO) {
        return ResponseEntity.ok().body(pickMeService.write(Integer.parseInt(userId), pickMeDTO));
    }

    /**
     * pickMe update
     */
    @PutMapping("/pickme/update")
    public ResponseEntity<Void> pickMeUpdate(@AuthenticationPrincipal String userId, @RequestBody PickMeDTO pickMeDTO) {
        pickMeService.update(Integer.parseInt(userId), pickMeDTO);
        return ResponseEntity.ok().build();
    }

    /**
     * pickMe delete
     */
    @DeleteMapping("/pickme/delete")
    public ResponseEntity<Void> pickMeDelete(@AuthenticationPrincipal String userId, @RequestParam int pickMeId) {
        pickMeService.delete(Integer.parseInt(userId), pickMeId);
        return ResponseEntity.ok().build();
    }
}
