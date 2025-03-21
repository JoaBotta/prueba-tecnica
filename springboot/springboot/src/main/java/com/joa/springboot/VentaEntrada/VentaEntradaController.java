package com.joa.springboot.VentaEntrada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas-entrada")
public class VentaEntradaController {

    @Autowired
    private VentaEntradaService ventaEntradaService;

    // ✅ Crear una nueva venta de entrada
    @PostMapping
    public ResponseEntity<VentaEntradaResponseDTO> createVentaEntrada(@RequestBody VentaEntradaRequestDTO requestDTO) {
        return ResponseEntity.ok(ventaEntradaService.createVentaEntrada(requestDTO));
    }

    // ✅ Obtener todas las ventas de entrada
    @GetMapping
    public ResponseEntity<List<VentaEntradaResponseDTO>> getAllVentasEntrada() {
        return ResponseEntity.ok(ventaEntradaService.getAllVentasEntrada());
    }

    // ✅ Obtener una venta de entrada por ID
    @GetMapping("/{id}")
    public ResponseEntity<VentaEntradaResponseDTO> getVentaEntradaById(@PathVariable Long id) {
        return ResponseEntity.ok(ventaEntradaService.getVentaEntradaById(id));
    }

    // ✅ Eliminar una venta de entrada por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVentaEntrada(@PathVariable Long id) {
        ventaEntradaService.deleteVentaEntrada(id);
        return ResponseEntity.noContent().build();
    }
}
