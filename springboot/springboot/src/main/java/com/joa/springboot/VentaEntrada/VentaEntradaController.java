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

    // ✅ Crear una venta de entrada (Genera un QR automáticamente)
    @PostMapping
    public ResponseEntity<VentaEntradaResponseDTO> createVentaEntrada(@RequestBody VentaEntradaRequestDTO requestDTO) {
        VentaEntradaResponseDTO responseDTO = ventaEntradaService.createVentaEntrada(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    // ✅ Obtener todas las ventas de entrada
    @GetMapping
    public ResponseEntity<List<VentaEntradaResponseDTO>> getAllVentasEntrada() {
        List<VentaEntradaResponseDTO> responseDTOs = ventaEntradaService.getAllVentasEntrada();
        return ResponseEntity.ok(responseDTOs);
    }

    // ✅ Obtener una venta de entrada por ID
    @GetMapping("/{id}")
    public ResponseEntity<VentaEntradaResponseDTO> getVentaEntradaById(@PathVariable Long id) {
        VentaEntradaResponseDTO responseDTO = ventaEntradaService.getVentaEntradaById(id);
        return ResponseEntity.ok(responseDTO);
    }

    // ✅ Obtener todas las ventas realizadas en un Punto de Venta específico
    @GetMapping("/punto-venta/{puntoVentaId}")
    public ResponseEntity<List<VentaEntradaResponseDTO>> getVentasByPuntoDeVenta(@PathVariable Long puntoVentaId) {
        List<VentaEntradaResponseDTO> responseDTOs = ventaEntradaService.getVentasByPuntoDeVenta(puntoVentaId);
        return ResponseEntity.ok(responseDTOs);
    }

    // ✅ Eliminar una venta de entrada
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVentaEntrada(@PathVariable Long id) {
        ventaEntradaService.deleteVentaEntrada(id);
        return ResponseEntity.noContent().build();
    }
}
