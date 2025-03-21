package com.joa.springboot.DetalleVentaFisica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalle-venta-fisica")
public class DetalleVentaFisicaController {

    @Autowired
    private DetalleVentaFisicaService detalleVentaFisicaService;

    // Crear un nuevo detalle de venta física
    @PostMapping
    public ResponseEntity<DetalleVentaFisicaResponseDTO> crearDetalle(@RequestBody DetalleVentaFisicaRequestDTO dto) {
        DetalleVentaFisicaResponseDTO detalle = detalleVentaFisicaService.crearDetalle(dto);
        return ResponseEntity.ok(detalle);
    }

    // Obtener todos los detalles de una venta física específica
    @GetMapping("/{ventaId}")
    public ResponseEntity<List<DetalleVentaFisicaResponseDTO>> obtenerDetallesPorVenta(@PathVariable Long ventaId) {
        List<DetalleVentaFisicaResponseDTO> detalles = detalleVentaFisicaService.obtenerDetallesPorVenta(ventaId);
        return ResponseEntity.ok(detalles);
    }

    // Eliminar un detalle de venta física
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDetalle(@PathVariable Long id) {
        detalleVentaFisicaService.eliminarDetalle(id);
        return ResponseEntity.noContent().build();
    }
}
