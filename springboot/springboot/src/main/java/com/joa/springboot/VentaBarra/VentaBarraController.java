package com.joa.springboot.VentaBarra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas-barra")
public class VentaBarraController {

    @Autowired
    private VentaBarraService ventaBarraService;

    @PostMapping
    public ResponseEntity<VentaBarraResponseDTO> createVentaBarra(@RequestBody VentaBarraRequestDTO requestDTO) {
        VentaBarraResponseDTO responseDTO = ventaBarraService.createVentaBarra(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<VentaBarraResponseDTO>> getAllVentasBarra() {
        List<VentaBarraResponseDTO> responseDTOs = ventaBarraService.getAllVentasBarra();
        return ResponseEntity.ok(responseDTOs);
    }
    @GetMapping("/{barraId}")
        public ResponseEntity<List<VentaBarraResponseDTO>> getVentasByBarra(@PathVariable Long barraId) {
            List<VentaBarraResponseDTO> ventas = ventaBarraService.getVentasByBarra(barraId);
            return ResponseEntity.ok(ventas);
        }
}
