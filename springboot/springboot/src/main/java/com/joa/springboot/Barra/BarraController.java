package com.joa.springboot.Barra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/barra")
public class BarraController {

    @Autowired
    private BarraService barraService;

    @PostMapping
    public ResponseEntity<BarraResponseDTO> createBarra(@RequestBody BarraRequestDTO requestDTO) {
        BarraResponseDTO responseDTO = barraService.createBarra(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<BarraResponseDTO>> getAllBarras() {
        List<BarraResponseDTO> responseDTOs = barraService.getAllBarras();
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BarraResponseDTO> getBarraById(@PathVariable Long id) {
        BarraResponseDTO responseDTO = barraService.getBarraById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BarraResponseDTO> updateBarra(@PathVariable Long id, @RequestBody BarraRequestDTO requestDTO) {
        BarraResponseDTO responseDTO = barraService.updateBarra(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBarra(@PathVariable Long id) {
        barraService.deleteBarra(id);
        return ResponseEntity.noContent().build();
    }
}
