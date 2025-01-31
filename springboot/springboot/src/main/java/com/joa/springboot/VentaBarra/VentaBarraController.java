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

    // Crear una venta en la barra
    @PostMapping
    public ResponseEntity<VentaBarraResponseDTO> createVentaBarra(@RequestBody VentaBarraRequestDTO requestDTO) {
        VentaBarraResponseDTO responseDTO = ventaBarraService.createVentaBarra(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    // Obtener todas las ventas de barra
    @GetMapping
    public ResponseEntity<List<VentaBarraResponseDTO>> getAllVentasBarra() {
        List<VentaBarraResponseDTO> responseDTOs = ventaBarraService.getAllVentasBarra();
        return ResponseEntity.ok(responseDTOs);
    }

    // Obtener todas las ventas por barra específica
    @GetMapping("/barra/{barraId}")
    public ResponseEntity<List<VentaBarraResponseDTO>> getVentasByBarra(@PathVariable Long barraId) {
        List<VentaBarraResponseDTO> ventas = ventaBarraService.getVentasByBarra(barraId);
        return ResponseEntity.ok(ventas);
    }

    // Obtener una venta por ID
    @GetMapping("/{ventaId}")
    public ResponseEntity<VentaBarraResponseDTO> getVentaById(@PathVariable Long ventaId) {
        VentaBarraResponseDTO venta = ventaBarraService.getVentaById(ventaId);
        return ResponseEntity.ok(venta);
    }

    // Eliminar una venta
    @DeleteMapping("/{ventaId}")
    public ResponseEntity<Void> deleteVenta(@PathVariable Long ventaId) {
        ventaBarraService.deleteVenta(ventaId);
        return ResponseEntity.noContent().build();
    }
}
