package com.joa.springboot.DetalleVentaEntrada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles-venta-Entrada")
public class DetalleVentaEntradaController {

    @Autowired
    private DetalleVentaEntradaService detalleVentaEntradaService;

    @PostMapping
    public ResponseEntity<DetalleVentaEntradaResponseDTO> createDetalleVentaEntrada(@RequestBody DetalleVentaEntradaRequestDTO requestDTO) {
        DetalleVentaEntradaResponseDTO responseDTO = detalleVentaEntradaService.createDetalleVentaEntrada(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<DetalleVentaEntradaResponseDTO>> getAllDetallesVentaEntrada() {
        List<DetalleVentaEntradaResponseDTO> responseDTOs = detalleVentaEntradaService.getAllDetallesVentaEntrada();
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleVentaEntradaResponseDTO> getDetalleVentaEntradaById(@PathVariable Long id) {
        DetalleVentaEntradaResponseDTO responseDTO = detalleVentaEntradaService.getDetalleVentaEntradaById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetalleVentaEntrada(@PathVariable Long id) {
        detalleVentaEntradaService.deleteDetalleVentaEntrada(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/venta/{ventaEntradaId}")
    public ResponseEntity<List<DetalleVentaEntradaResponseDTO>> getDetallesByVentaEntrada(@PathVariable Long ventaEntradaId) {
        List<DetalleVentaEntradaResponseDTO> responseDTOs = detalleVentaEntradaService.getDetallesByVentaEntrada(ventaEntradaId);
        return ResponseEntity.ok(responseDTOs);
    }
}
