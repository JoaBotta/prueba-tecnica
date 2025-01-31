package com.joa.springboot.DetalleVentaEntrada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles-venta-entrada")
public class DetalleVentaEntradaController {

    @Autowired
    private DetalleVentaEntradaService detalleVentaEntradaService;

    // ✅ Crear un nuevo detalle de venta de entrada
    @PostMapping("/{ventaEntradaId}")
    public ResponseEntity<DetalleVentaEntradaResponseDTO> createDetalleVentaEntrada(
            @PathVariable Long ventaEntradaId,
            @RequestBody DetalleVentaEntradaRequestDTO requestDTO) {
        DetalleVentaEntradaResponseDTO responseDTO = detalleVentaEntradaService.createDetalleVentaEntrada(ventaEntradaId, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    // ✅ Obtener todos los detalles de una venta de entrada
    @GetMapping("/{ventaEntradaId}")
    public ResponseEntity<List<DetalleVentaEntradaResponseDTO>> getDetallesByVentaEntrada(@PathVariable Long ventaEntradaId) {
        List<DetalleVentaEntradaResponseDTO> responseDTOs = detalleVentaEntradaService.getDetallesByVentaEntrada(ventaEntradaId);
        return ResponseEntity.ok(responseDTOs);
    }

    // ✅ Eliminar un detalle de venta de entrada
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetalleVentaEntrada(@PathVariable Long id) {
        detalleVentaEntradaService.deleteDetalleVentaEntrada(id);
        return ResponseEntity.noContent().build();
    }
}
