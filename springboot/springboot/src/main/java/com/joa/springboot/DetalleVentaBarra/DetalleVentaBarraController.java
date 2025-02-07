package com.joa.springboot.DetalleVentaBarra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.joa.springboot.VentaBarra.VentaBarra;
import com.joa.springboot.VentaBarra.VentaBarraService;

import java.util.List;

@RestController
@RequestMapping("/api/detalles-venta-barra")
public class DetalleVentaBarraController {

    @Autowired
    private DetalleVentaBarraService detalleVentaBarraService;

    @Autowired
    private VentaBarraService ventaBarraService; // 🔹 Se usa para buscar la venta antes de crear detalles

    @PostMapping("/{ventaBarraId}")
    public ResponseEntity<DetalleVentaBarraResponseDTO> createDetalleVentaBarra(
            @PathVariable Long ventaBarraId,
            @RequestBody DetalleVentaBarraRequestDTO requestDTO) {

        // Buscar la VentaBarra asociada correctamente
        VentaBarra ventaBarra = ventaBarraService.getVentaByIdEntity(ventaBarraId);

        // Crear el detalle de venta
        DetalleVentaBarraResponseDTO responseDTO = detalleVentaBarraService.createDetalleVentaBarra(ventaBarra, requestDTO);
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
