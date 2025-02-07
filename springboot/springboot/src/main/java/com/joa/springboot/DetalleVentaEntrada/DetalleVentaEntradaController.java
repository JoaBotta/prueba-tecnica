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

    @PostMapping
    public ResponseEntity<DetalleVentaEntradaResponseDTO> createDetalleVentaEntrada(@RequestBody DetalleVentaEntradaRequestDTO requestDTO) {
        DetalleVentaEntradaResponseDTO responseDTO = detalleVentaEntradaService.createDetalleVentaEntrada(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<DetalleVentaEntradaResponseDTO>> getAllDetallesVentaEntrada() {
        return ResponseEntity.ok(detalleVentaEntradaService.getAllDetallesVentaEntrada());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleVentaEntradaResponseDTO> getDetalleVentaEntradaById(@PathVariable Long id) {
        return ResponseEntity.ok(detalleVentaEntradaService.getDetalleVentaEntradaById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetalleVentaEntrada(@PathVariable Long id) {
        detalleVentaEntradaService.deleteDetalleVentaEntrada(id);
        return ResponseEntity.noContent().build();
    }
}
