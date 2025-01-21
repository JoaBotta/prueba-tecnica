package com.joa.springboot.Boliche;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boliches")
public class BolicheController {

    @Autowired
    private BolicheService bolicheService;

    @PostMapping
    public ResponseEntity<BolicheResponseDTO> createBoliche(@RequestBody BolicheRequestDTO requestDTO) {
        BolicheResponseDTO responseDTO = bolicheService.createBoliche(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<BolicheResponseDTO>> getAllBoliches() {
        List<BolicheResponseDTO> responseDTOs = bolicheService.getAllBoliches();
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BolicheResponseDTO> getBolicheById(@PathVariable Long id) {
        BolicheResponseDTO responseDTO = bolicheService.getBolicheById(id);
        return ResponseEntity.ok(responseDTO);

    }

    
}
