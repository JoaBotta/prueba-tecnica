package com.joa.springboot.DetalleVentaBarra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles-venta-barra")
public class DetalleVentaBarraController {

    @Autowired
    private DetalleVentaBarraService detalleVentaBarraService;

    @PostMapping
    public ResponseEntity<DetalleVentaBarraResponseDTO> createDetalleVentaBarra(@RequestBody DetalleVentaBarraRequestDTO requestDTO) {
        DetalleVentaBarraResponseDTO responseDTO = detalleVentaBarraService.createDetalleVentaBarra(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<DetalleVentaBarraResponseDTO>> getAllDetallesVentaBarra() {
        List<DetalleVentaBarraResponseDTO> responseDTOs = detalleVentaBarraService.getAllDetallesVentaBarra();
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleVentaBarraResponseDTO> getDetalleVentaBarraById(@PathVariable Long id) {
        DetalleVentaBarraResponseDTO responseDTO = detalleVentaBarraService.getDetalleVentaBarraById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetalleVentaBarra(@PathVariable Long id) {
        detalleVentaBarraService.deleteDetalleVentaBarra(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/venta/{ventaBarraId}")
    public ResponseEntity<List<DetalleVentaBarraResponseDTO>> getDetallesByVentaBarra(@PathVariable Long ventaBarraId) {
        List<DetalleVentaBarraResponseDTO> responseDTOs = detalleVentaBarraService.getDetallesByVentaBarra(ventaBarraId);
        return ResponseEntity.ok(responseDTOs);
    }
}
